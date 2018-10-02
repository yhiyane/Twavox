package main;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

public class FtpAnalyzer {

	public static void main(String[] args) throws InvalidFileFormatException, IOException {
		Wini ini = IniUtils.getIni();
		Section ftpSection = ini.get("ftp");
		String url = ftpSection.get("ip");
		String login = ftpSection.get("login");
		String password = ftpSection.get("password");
		String path = ftpSection.get("path");

		// get an ftpClient object
		FTPClient ftpClient = new FTPClient();

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
}
