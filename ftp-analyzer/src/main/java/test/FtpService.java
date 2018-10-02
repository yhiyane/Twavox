/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package test;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpService {

	private FTPClient client;
	private boolean wantToStop = false;
	private boolean isScanning = false;

	public FtpService() {
	}

	private void disconnect() {
		if (client != null) {
			try {
				client.logout();
			} catch (IOException e) {
			}
			client = null;
		}
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
	public void scanFtpServer(String host, String path, String login, String password) {
		if (!wantToStop) {
			try {
				client = FtpUtils.connect(host, login, password);
				if (client != null) {
					System.out.println("Connection established...");
					System.out.println("Status: " + client.getStatus());

					System.out.println("List in " + path + " :");
					for (FTPFile ftp : client.listFiles(path)) {
						System.out.println(ftp.getName() + " " + ftp.getSize());
					}
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
	}
}
