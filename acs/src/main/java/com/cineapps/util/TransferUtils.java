/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class TransferUtils {

	private static final Logger logger = Logger.getLogger(TransferUtils.class);

	// @formatter:off
	/**
	 * Copy the directory from the source path to the destination path.
	 * 
	 * @param sourcePath : the source path 
	 * @param destPath : the destination path
	 * @param sourceClient : the FTP client
	 */
	// @formatter:on
	public static void copyDirectory(String sourcePath, String destPath, FTPClient sourceClient) {
		try {
			sourcePath += sourcePath.endsWith("/") ? "" : "/";
			destPath += destPath.endsWith("/") ? "" : "/";
			File destFile = new File(destPath);
			if (!destFile.exists() && !destFile.mkdirs()) {
				logger.error("Cannot create dirs " + destFile.getPath());
			}
			copy(sourcePath, destPath, sourceClient);
		} catch (IOException e) {
			logger.error("Error when copying directory from " + sourcePath + " to " + destPath);
			logger.error(e);
		}
	}

	// @formatter:off
	/**
	 * Copy the directory from the source path to the destination path.
	 * 
	 * @param sourcePath : the source path 
	 * @param destPath : the destination path
	 * @param sourceClient : the FTP client
	 */
	// @formatter:on
	private static void copy(String sourcePath, String destPath, FTPClient sourceClient)
	        throws IOException {
		FTPFile[] sourceFiles = sourcePath == null ? sourceClient.listFiles() : sourceClient
		        .listFiles(sourcePath);
		for (FTPFile ftpFile : sourceFiles) {
			String fileName = ftpFile.getName();
			// Skip . and ..
			if (fileName.equals(".") || fileName.equals("..")) {
				continue;
			}
			// Recursivity on folders
			else if (ftpFile.isDirectory()) {
				String destDirectory = destPath + fileName;
				File dest = new File(destDirectory);
				if (!dest.exists() && !dest.mkdirs()) {
					logger.info("Cannot create the directory " + destDirectory);
				}
				copy(sourcePath + fileName + "/", destDirectory + "/", sourceClient);
			}
			// Copy file
			else if (ftpFile.isFile() && isDownlable(fileName)) {
				InputStream input = null;
				OutputStream output = null;
				File destFile = new File(destPath + fileName);

				if (!destFile.exists()) {
					try {
						logger.info("transfer " + sourcePath + fileName + " to " + destPath
						        + fileName);
						input = sourceClient.retrieveFileStream(sourcePath + fileName);
						output = new FileOutputStream(destFile);
						byte[] buf = new byte[1024];
						int bytesRead;
						while ((bytesRead = input.read(buf)) > 0) {
							output.write(buf, 0, bytesRead);
						}
					} catch (Exception e) {
						logger.error("Error when writing file from " + sourcePath + " to "
						        + destPath);
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
							logger.error("Cannot close input/output streams");
							logger.error(ioe);
						}
						sourceClient.completePendingCommand();
					}
				}
			}
		}
	}

	// @formatter:off
	/**
	 * Returns true if the file can be downloaded, false otherwise. (expect cpl
	 * and subtitle files only)
	 * 
	 * @param fileName : the name of the file 
	 * @return
	 */
	// @formatter:on
	public static boolean isDownlable(String fileName) {
		if (fileName.equals(".") || fileName.equals("..")) {
			return false;
		}
		// Takes only xml files except pkl and assetmap
		if (fileName.endsWith(".xml") && !fileName.toLowerCase().contains("pkl")
		        && !fileName.toLowerCase().contains("assetmap")) {
			return true;
		}
		return false;
	}
}
