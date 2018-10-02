/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.cineapps.manager.CplFilesManager;
import com.cineapps.model.core.Ftp;
import com.cineapps.model.cpl.CompositionPlaylist;
import com.cineapps.model.property.FtpProperty;
import com.cineapps.model.property.PropertyLoader;
import com.cineapps.util.DcpNameUtils;
import com.cineapps.util.FileSystemUtils;
import com.cineapps.util.FtpUtils;
import com.cineapps.util.TransferUtils;

public class FtpService implements IService {

	private static final Logger logger = Logger.getLogger(FtpService.class);
	private static final int MIN_SPACE_PERCENT = 20;
	private static final String JOB_GROUP = "FtpJobGroup";

	private FTPClient client;
	private boolean wantToStop = false;
	private boolean isScanning = false;

	public FtpService() {
	}

	@Override
	public void startProcess() {
		// Do nothing special
	}

	@Override
	public void stopProcess() {
		disconnect();
	}

	private void disconnect() {
		if (client != null) {
			try {
				client.logout();
			} catch (IOException e) {
				logger.error(e);
			}
			logger.info("ftp disconnection");
			client = null;
		}
	}

	@Override
	public String getJobGroup() {
		return JOB_GROUP;
	}

	/**
	 * Notify the service to stop the process as soon as possible. If the
	 * service is downloading a folder from the ftp server, it will wait for the
	 * end of the download before to stop the process. We have to do that to
	 * avoid to process with incomplete data.
	 */
	public void notifyWantToStop() {
		if (isScanning) {
			wantToStop = true;
		} else {
			isScanning = false;
			wantToStop = false;
		}
	}

	/**
	 * Connect to the ftp and downloads all files related with the current
	 * playing cpl. By convention, all the files are stored in the same
	 * directory named by the prefix.
	 * 
	 * @param cplName
	 *            : the name of the cpl of the movie.
	 * @return
	 * @throws IOException
	 */
	public CplFilesManager downloadIfNeeded(String cplName, MonitoringService monitoringService)
	        throws IOException {
		if (cplName == null) {
			logger.error("Download directories from FTP with null parameter");
			monitoringService.monitorError("[Configuration]",
			        "Attempt to scan the FTP library without cpl name");
			return null;
		}

		// Get the properties
		PropertyLoader propertyLoader = ServicesHolder.getInstance().getPropertyLoader();
		FtpProperty ftpProperty = propertyLoader.getFtpProperty();
		String subtitleRootDirectoryUrl = propertyLoader.getSubtitleProperty()
		        .getSubtitlesRootDirectoryUrl();
		subtitleRootDirectoryUrl += subtitleRootDirectoryUrl.endsWith("/") ? "" : "/";
		String prefix = DcpNameUtils.getMovieName(cplName);
		String destPath = subtitleRootDirectoryUrl + prefix;

		// Create the root directory if not exists
		File subtitleRootDirectory = new File(subtitleRootDirectoryUrl);
		if (!subtitleRootDirectory.exists() && !subtitleRootDirectory.mkdirs()) {
			throw new IOException("Cannot create directory");
		}

		// Clean the root directory if needed
		boolean cleaned = cleanDirectoryIfNeeded(subtitleRootDirectoryUrl, destPath);
		// Update the status with the list of subtitle folders
		monitoringService.updateStatus();
		if (!cleaned) {
			monitoringService.monitorError("[Twavox]", "No disk space available");
			throw new IOException("No disk space available");
		}

		File destDirectory = new File(destPath);

		// Scan the ftp to look for subtitle files if no subtitles was found
		if (!destDirectory.exists()) {
			isScanning = true;
			// monitoring
			monitoringService.monitorScanLibrary(cplName);

			// Scans FTP servers
			for (Ftp ftp : ftpProperty.getFtps()) {
				scanFtpServer(ftp, prefix, destPath, monitoringService);
			}

			// Update the status with the list of subtitle folders
			monitoringService.updateStatus();
			wantToStop = false;
			isScanning = false;
		}

		// Returns the list of cpls
		return FileSystemUtils.findCplFiles(destPath);
	}

	/**
	 * Connects to the FTP server and looks for subtitle files related with the
	 * prefix cpl name.
	 * 
	 * @param ftpServer
	 *            the FTP server to scan
	 * @param prefix
	 *            the name of the movie in the cpl name
	 * @param destPath
	 *            the path to store the downloaded files
	 * @param monitoringService
	 */
	private void scanFtpServer(Ftp ftpServer, String prefix, String destPath,
	        MonitoringService monitoringService) {
		if (!wantToStop) {
			try {
				client = FtpUtils.connect(ftpServer.getHost(), ftpServer.getUser(),
				        ftpServer.getPassword());
				String ftpUrl = ftpServer.getHost() + ":" + ftpServer.getUser() + ":"
				        + ftpServer.getPassword();
				if (client != null) {
					logger.info("ftp connected : " + ftpUrl);
					logger.info("ftp scanning...");

					downloadIfNeeded(ftpServer.getPath(), destPath, prefix, monitoringService);
				} else {
					logger.error("The ftp client is null due to connection failure");
					monitoringService.monitorError("[FTP Scan]", "Connection to FTP failed @ "
					        + ftpUrl);
				}
			} finally {
				disconnect();
			}
		}
	}

	/**
	 * Removes all directories except those in the parameter if needed. Returns
	 * true if the available space size is superior than the minimum size, false
	 * otherwise.
	 * 
	 * @param rootDirectoryPath
	 *            : the root directory path
	 * @param excludeDirectoryPath
	 *            : the directory path to not delete
	 * @throws IOException
	 */
	private boolean cleanDirectoryIfNeeded(String rootDirectoryPath, String excludeDirectoryPath)
	        throws IOException {
		int availablePercent = FileSystemUtils.getAvailablePercent(rootDirectoryPath);
		if (availablePercent <= MIN_SPACE_PERCENT) {
			for (File file : new File(rootDirectoryPath).listFiles()) {
				if (!file.getPath().equals(excludeDirectoryPath)) {
					try {
						FileUtils.deleteDirectory(file);
					} catch (IOException e) {
						logger.error("Cannot delete directory " + file.getName());
						logger.error(e);
					}
				}
			}
		}
		return FileSystemUtils.getAvailablePercent(rootDirectoryPath) > MIN_SPACE_PERCENT;
	}

	/**
	 * Search in the ftp server all directories the subtitle files related with
	 * the current played movie.
	 * 
	 */
	private void downloadIfNeeded(String srcPath, String destPath, String prefix,
	        MonitoringService monitoringService) {
		srcPath += srcPath.endsWith("/") ? "" : "/";
		destPath += destPath.endsWith("/") ? "" : "/";

		// Get the list of files in the path
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = client.listFiles(srcPath);
			logger.info(ftpFiles.length + " files in the FTP");
		} catch (IOException e) {
			logger.error("Cannot get files from the ftp in " + srcPath);
			logger.error(e);
		}

		// Look for the xml files
		List<String> xmlFiles = new ArrayList<>();
		for (FTPFile ftpFile : ftpFiles) {
			String name = ftpFile.getName();
			if (ftpFile.isFile() && TransferUtils.isDownlable(name)) {
				xmlFiles.add(name);
			}
		}

		// Check if there is at least one CPL file
		CompositionPlaylist cpl = null;
		for (String xmlFile : xmlFiles) {
			try {
				// Download the cpl file in a temp file
				File temp = File.createTempFile("tempCpl", ".xml");
				OutputStream output = new FileOutputStream(temp);
				client.retrieveFile(srcPath + xmlFile, output);
				output.flush();
				output.close();

				// Check if the cpl contains any subtitles
				cpl = CompositionPlaylist.fromFile(temp.getAbsolutePath());

				// Delete temp file
				if (!temp.delete()) {
					logger.error("Cannot delete the file " + temp.getPath());
				}

				// Quit the loop because find a cpl file
				break;
			} catch (IOException ioe) {
				logger.error(ioe);
			}
		}

		// Download the directory if the CPL is related with the current played
		// movie
		if (cpl != null) {
			if (cpl.getContentTitleText().toLowerCase().startsWith(prefix.toLowerCase())) {
				// monitoring
				monitoringService.monitorDownload();
				// Transfer
				TransferUtils.copyDirectory(srcPath, destPath, client);
			}
		} else {
			// Recursive call on sub folders
			for (FTPFile ftpFile : ftpFiles) {
				String name = ftpFile.getName();
				if (ftpFile.isDirectory() && !name.equals(".") && !name.equals("..")
				        && !name.startsWith("__")) {
					downloadIfNeeded(srcPath + name, destPath, prefix, monitoringService);
				}
			}
		}

	}
}
