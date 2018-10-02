/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.gdc.sample;

import com.cineapps.server.gdc.server.GdcServer;

public class SampleGdcServer {

	public static void main(String[] args) {
		int port = 49153;
		GdcServer server = new GdcServer("title");
		server.run(port);
	}
}
