/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package test;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Test {

	private static void run() {
		String url = "10.153.0.20";
		String login = "cabine";
		String password = "password";
		String path = "/DCP/FILM/";

		// get an ftpClient object
		FTPClient ftpClient = new FTPClient();
		System.out.println("TEST");

		try {
			// pass directory path on server to connect
			System.out.println("Connect to " + url);
			ftpClient.connect(url);
			// pass username and password, returned true if authentication is
			// successful
			System.out.println("Login with " + login + " / " + password);
			boolean connected = ftpClient.login(login, password);

			if (connected) {
				System.out.println("Connection established...");
				System.out.println("Status: " + ftpClient.getStatus());

				System.out.println("List in " + path + " :");
				for (FTPFile ftp : ftpClient.listFiles(path)) {
					System.out.println(ftp.getName() + " " + ftp.getSize());
				}

				// logout the user, returned true if logout successfully
				boolean logout = ftpClient.logout();
				if (logout) {
					System.out.println("Connection close...");
				}
			} else {
				System.out.println("Connection fail...");
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		FtpService servie = new FtpService();
		servie.scanFtpServer("10.153.0.20", "/DCP/FILM/", "cabine", "password");

	}
}
