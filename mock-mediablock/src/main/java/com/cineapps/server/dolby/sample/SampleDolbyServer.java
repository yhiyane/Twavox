/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.dolby.sample;

import com.cineapps.server.dolby.server.DolbyServer;

public class SampleDolbyServer {

	public static void main(String[] args) {
		String host = "10.1.1.3";
		int port = 8080;

		DolbyServer dolbyServer = new DolbyServer(host, "");
		dolbyServer.run(port);
	}
}
