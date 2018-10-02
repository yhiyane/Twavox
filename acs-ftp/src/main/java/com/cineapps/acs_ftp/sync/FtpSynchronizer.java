/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.acs_ftp.sync;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.cineapps.acs_ftp.model.CompositionPlaylist;
import com.cineapps.acs_ftp.model.CompositionPlaylist.ReelList.Reel;
import com.cineapps.acs_ftp.model.Ftp;
import com.cineapps.acs_ftp.utils.FtpUtils;

public class FtpSynchronizer {

	private static final Logger logger = Logger.getLogger(FtpSynchronizer.class);

	/**
	 * Synchronizes the subtitle files with the ftp server.
	 * 
	 * @param subtitlesDirPath
	 * @param ftps
	 */
	public static void synchronize(String subtitlesDirPath, List<Ftp> ftps) {
		logger.info("Synchronization...");
		// Get the list of subtitle dirs
		File subtitlesDir = new File(subtitlesDirPath);
		if (!subtitlesDir.exists() && !subtitlesDir.mkdirs()) {
			logger.error("Cannot create the subtitles directory");
		}
		List<String> subtitlesDirBefore = new ArrayList<String>(Arrays.asList(subtitlesDir.list()));

		// Download subtitle files from ftp servers
		Set<String> newSubtitleDirs = new TreeSet<>();
		for (Ftp ftp : ftps) {
			FTPClient client = FtpUtils.connect(ftp.getHost(), ftp.getUser(), ftp.getPassword());
			if (client == null) {
				logger.error("Client is null");
			} else {
				downloadIfNeeded(client, ftp.getPath(), subtitlesDirPath, newSubtitleDirs);
				try {
					client.disconnect();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		// Delete old subtitle dirs
		deleteOldSubDirs(subtitlesDirPath, subtitlesDirBefore, newSubtitleDirs);
		logger.info("...done");
	}

	/**
	 * Search in the ftp server all directories with subtitle files.
	 * 
	 * @param client
	 * @param path
	 * @param subtitlesDirPath
	 * @param newSubtitleDirs
	 */
	private static void downloadIfNeeded(FTPClient client, String path, String subtitlesDirPath,
	        Set<String> newSubtitleDirs) {
		path += path.endsWith("/") ? "" : "/";
		subtitlesDirPath += subtitlesDirPath.endsWith("/") ? "" : "/";

		// Get the list of files in the path
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = client.listFiles(path);
		} catch (IOException e) {
			logger.error("Cannot get files from the ftp in " + path);
			logger.error(e);
		}

		// Look for the xml files
		boolean containsXmlFiles = false;
		List<String> xmlFiles = new ArrayList<>();
		for (FTPFile ftpFile : ftpFiles) {
			String name = ftpFile.getName();
			if (ftpFile.isFile() && isDownloable(name)) {
				containsXmlFiles = true;
				xmlFiles.add(name);
			}
		}

		if (containsXmlFiles) {
			for (String xmlFile : xmlFiles) {
				try {
					// Download the cpl file in a temp file
					File temp = File.createTempFile("tempCpl", ".xml");
					OutputStream output = new FileOutputStream(temp);
					client.retrieveFile(path + xmlFile, output);
					output.flush();
					output.close();

					// Check if the cpl contains any subtitles
					CompositionPlaylist cpl = CompositionPlaylist.fromFile(temp.getAbsolutePath());
					if (cpl != null) {
						boolean containsSubtitles = false;
						for (Reel reel : cpl.getReelList().getReel()) {
							if (reel.getAssetList().getMainSubtitle() != null) {
								containsSubtitles = true;
								break;
							}
						}
						if (containsSubtitles) {
							// Get the prefix of the cpl
							String subtitleDirName = cpl.getContentTitleText().split("_")[0];
							transferIfNeeded(client, path, subtitlesDirPath + subtitleDirName);
							newSubtitleDirs.add(subtitleDirName);
							break;
						}
					}
					// Delete temp file
					if (!temp.delete()) {
						logger.error("Cannot delete the file " + temp.getPath());
					}
				} catch (IOException ioe) {
					logger.error(ioe);
				}
			}
		} else {
			// Recursive call on sub folders
			for (FTPFile ftpFile : ftpFiles) {
				downloadIfNeeded(client, path + ftpFile.getName(), subtitlesDirPath,
				        newSubtitleDirs);
			}
		}

	}

	/**
	 * Transfer the directory from the ftp server to the local file system.
	 * 
	 * @param client
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	private static void transferIfNeeded(FTPClient client, String src, String dest)
	        throws IOException {
		src += src.endsWith("/") ? "" : "/";
		dest += dest.endsWith("/") ? "" : "/";
		FTPFile[] ftpFiles = client.listFiles(src);

		for (FTPFile ftpFile : ftpFiles) {
			String fileName = ftpFile.getName();

			// Recursivity on sub folders
			if (ftpFile.isDirectory()) {
				transferIfNeeded(client, src + fileName + "/", dest + fileName + "/");
			}
			// Copy the file if it not exists
			else {
				// Create the directory if needed
				File destDirectory = new File(dest);
				if (!destDirectory.exists() && !destDirectory.mkdirs()) {
					logger.error("Cannot create the directory " + dest);
				}

				File finalFile = new File(dest + fileName);
				if (ftpFile.isFile() && !finalFile.exists() && isDownloable(fileName)) {
					logger.info("Transfer " + src + " to " + dest);
					InputStream input = null;
					OutputStream output = null;
					try {
						input = client.retrieveFileStream(src + fileName);
						output = new FileOutputStream(finalFile);
						byte[] buf = new byte[1024];
						int bytesRead;
						while ((bytesRead = input.read(buf)) > 0) {
							output.write(buf, 0, bytesRead);
						}
					} catch (IOException e) {
						logger.error(e);
					} finally {
						try {
							if (input != null) {
								input.close();
							}
							if (output != null) {
								output.close();
							}
						} catch (IOException ioe) {
							logger.error(ioe);
						}
						client.completePendingCommand();
					}
				}
			}
		}
	}

	/**
	 * Returns true if the file in the ftp can be downloaded, false otherwise.
	 * 
	 * @param fileName
	 * @return
	 */
	private static boolean isDownloable(String fileName) {
		// Don't want to download .mxf files, pkl file, assetmap file
		if (fileName.endsWith(".xml") && !fileName.toLowerCase().contains("pkl")) {
			return true;
		}
		return false;
	}

	private static void deleteOldSubDirs(String subtitlesDirPath, List<String> subtitlesDirBefore,
	        Set<String> newSubtitleDirs) {
		// Calculate the old directories
		for (String subtitleDirInFtp : newSubtitleDirs) {
			subtitlesDirBefore.remove(subtitleDirInFtp);
		}

		// Remove old subtitle directories
		subtitlesDirPath += subtitlesDirPath.endsWith("/") ? "" : "/";
		for (String oldSubtitleDir : subtitlesDirBefore) {
			File toDelete = new File(subtitlesDirPath + oldSubtitleDir);
			if (toDelete.isDirectory()) {
				logger.info("Delete the directory " + oldSubtitleDir);
				try {
					FileUtils.deleteDirectory(toDelete);
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}
}
