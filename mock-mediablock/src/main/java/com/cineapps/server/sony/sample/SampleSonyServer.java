/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.sony.sample;

import com.cineapps.server.sony.server.SonyServer;

public class SampleSonyServer {

	public static void main(String[] args) {
		int port = 4433;
		String ip = "10.1.1.3";
		String keystorePath = "src/main/resources/sony/keystore";

		SonyServer server = new SonyServer(ip, "title", keystorePath);
		server.run(port);
	}
}
