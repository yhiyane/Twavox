/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.sony.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cineapps.server.sony.api.SonyApi;

@Path("/")
@Singleton
public class SonyResources {

	@Inject
	SonyApi sonyApi;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_XML)
	public String login() {
		return sonyApi.login();
	}

	@GET
	@Path("/playback/showstatus")
	@Produces(MediaType.APPLICATION_XML)
	public String getPlaybackStatus() {
		return sonyApi.getPlaybackStatus();
	}

	@POST
	@Path("/show/playlist/details/info")
	@Produces(MediaType.APPLICATION_XML)
	public String getSplInfo() {
		return sonyApi.getSplInfos();
	}

	@GET
	@Path("/command/mediablock/cpl/download")
	@Produces(MediaType.APPLICATION_XML)
	public String getCplInfo() {
		return sonyApi.getCplInfos();
	}

	@GET
	@Path("/status/list")
	@Produces(MediaType.APPLICATION_XML)
	public String getDeviceList() {
		return sonyApi.getDeviceList();
	}
}
