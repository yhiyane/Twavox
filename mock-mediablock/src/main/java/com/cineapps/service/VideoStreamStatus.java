/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VideoStreamStatus {

	private final static Logger logger = Logger.getLogger(VideoStreamStatus.class.getName());
	private int currentTime_s;
	private boolean isPlaying;

	private VideoStreamStatus() {

	}

	public int getCurrentTime_s() {
		return currentTime_s;
	}

	public void setCurrentTime_s(int currentTime_s) {
		this.currentTime_s = currentTime_s;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public static VideoStreamStatus fromString(String json) {
		VideoStreamStatus status = new VideoStreamStatus();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject obj = (JSONObject) jsonParser.parse(json);
			Double currentTime = (Double) obj.get("currentTime_s");
			Boolean isPlaying = (Boolean) obj.get("isPlaying");

			status.setCurrentTime_s((int) Math.round(currentTime));
			status.setPlaying(isPlaying);
		} catch (ParseException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return status;
	}
}
