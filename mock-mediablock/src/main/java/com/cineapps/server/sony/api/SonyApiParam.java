/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.sony.api;

import java.util.UUID;

import com.cineapps.service.VideoStreamWebService;

public class SonyApiParam {

	private String title;
	private UUID splUuid;
	private UUID cplUuid;
	private VideoStreamWebService videoStreamWebService;
	private int movieDurationSeconds;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UUID getSplUuid() {
		return splUuid;
	}

	public void setSplUuid(UUID splUuid) {
		this.splUuid = splUuid;
	}

	public UUID getCplUuid() {
		return cplUuid;
	}

	public void setCplUuid(UUID cplUuid) {
		this.cplUuid = cplUuid;
	}

	public VideoStreamWebService getVideoStreamWebService() {
		return videoStreamWebService;
	}

	public void setVideoStreamWebService(VideoStreamWebService videoStreamWebService) {
		this.videoStreamWebService = videoStreamWebService;
	}

	public int getMovieDurationSeconds() {
		return movieDurationSeconds;
	}

	public void setMovieDurationSeconds(int movieDurationSeconds) {
		this.movieDurationSeconds = movieDurationSeconds;
	}
}
