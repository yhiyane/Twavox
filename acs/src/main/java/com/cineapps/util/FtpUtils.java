/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

public class FtpUtils {

	private static Logger logger = Logger.getLogger(FtpUtils.class);
	private static final int FTP_CONNECTION_TIMEOUT = 5000;

	// @formatter:off
	/**
	 * Connects to the ftp client. 
	 * Returns the client if the connection is successful, null otherwise.
	 * 
	 * @param host
	 * @param login
	 * @param password
	 */
	// @formatter:on
	public static FTPClient connect(String host, String login, String password) {
		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(FTP_CONNECTION_TIMEOUT);
			ftpClient.connect(host);
			if (ftpClient.login(login, password)) {
				return ftpClient;
			}
		} catch (IOException e) {
			logger.error("Cannot connect to the ftp " + host + " with " + login + "/" + password);
			logger.error(e);
		}
		return null;
	}
}
