package com.cineapps.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cineapps.server.common.MediablockType;
import com.cineapps.server.dolby.server.DolbyServer;
import com.cineapps.server.doremi.DoremiServer;
import com.cineapps.server.gdc.server.GdcServer;
import com.cineapps.server.sony.server.SonyServer;

public class Main {

	private final static Logger logger = Logger.getLogger(Main.class.getName());
	// private final static String KEYSTORE_PATH =
	// Main.class.getResource("/keystore/keystore")
	// .getPath();
	private final static String KEYSTORE_PATH = "keystore/keystore";

	public static void main(final String[] args) throws FileNotFoundException, IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Expect : java -jar mock-mediablock path/to/config");
		}
		// load the properties
		Properties prop = new Properties();
		prop.load(new FileInputStream(args[0]));

		// Extract informations
		String ipAddress = prop.getProperty("server.ipAddress");
		int port = Integer.parseInt(prop.getProperty("server.port"));
		String stFolder = prop.getProperty("st.folder");
		MediablockType mediablockType = MediablockType.fromName(prop.getProperty("server.type"));
		final SystemType systemType = SystemType.fromName(prop.getProperty("system.type"));

		// Run the video-stream process
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				String currentPath = System.getProperty("user.dir");
				String nodeBinPath = null;
				String videoStreamProjectServerPath = currentPath + "/video-stream/server.js";
				switch (systemType) {
					case MAC:
						nodeBinPath = currentPath
						        + "/video-stream/node/node-v0.10.33-darwin-x86/bin/node";
						break;
					case WINDOWS:
						nodeBinPath = currentPath + "/video-stream/node/node-v0.10.33-x86.exe";
						break;
					case LINUX:
						nodeBinPath = currentPath
						        + "/video-stream/node/node-v0.12.0-linux-x86/bin/node";
						break;

					default:
						throw new IllegalArgumentException("Unknown server.type");
				}

				try {
					Runtime.getRuntime().exec(nodeBinPath + " " + videoStreamProjectServerPath);
				} catch (IOException e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
			}
		});
		// PROD : run the thread
		thread.run();

		// Run the mediablock server
		switch (mediablockType) {
			case DOREMI:
				DoremiServer doremi = new DoremiServer(stFolder);
				doremi.run(port);
				break;
			case DOLBY:
				DolbyServer dolby = new DolbyServer(ipAddress, stFolder);
				dolby.run(port);
				break;
			case GDC:
				GdcServer gdc = new GdcServer(stFolder);
				gdc.run(port);
				break;
			case SONY:
				SonyServer sony = new SonyServer(ipAddress, stFolder, KEYSTORE_PATH);
				sony.run(port);
				break;

			default:
				logger.log(Level.SEVERE, "Unknown server.type");
				break;
		}
	}

	public enum SystemType {

		MAC("MAC"), WINDOWS("DOREMI"), LINUX("LINUX");

		private final String name;
		private static List<SystemType> types = Arrays.asList(MAC, WINDOWS, LINUX);

		private SystemType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public static SystemType fromName(String name) {
			for (SystemType type : types) {
				if (type.getName().equals(name)) {
					return type;
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
