/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.sony.api;

public interface SonyApi {

	public String login();

	public String getPlaybackStatus();

	public String getSplInfos();

	public String getCplInfos();

	public String getDeviceList();
}
