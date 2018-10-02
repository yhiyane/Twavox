/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.dolby.server;

import javax.xml.ws.Endpoint;

import com.cineapps.server.common.MediablockServer;

public class DolbyServer implements MediablockServer {

	private final String host;
	private final String contentTitle;

	public DolbyServer(String host, String contentTitle) {
		this.host = host;
		this.contentTitle = contentTitle;
	}

	@Override
	public void run(int port) {
		PlaybackControlServiceImpl playbackControlServiceImpl = new PlaybackControlServiceImpl();
		Endpoint.publish(playbackControlServiceImpl.getEndPointUrl(host, port),
		        playbackControlServiceImpl);

		ShowManagementServiceImpl showManagementServiceImpl = new ShowManagementServiceImpl(
		        contentTitle);
		Endpoint.publish(showManagementServiceImpl.getEndPointUrl(host, port),
		        showManagementServiceImpl);
	}
}
