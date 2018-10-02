/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.sony.api;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import com.cineapps.service.VideoStreamStatus;

public class SonyApiImpl implements SonyApi {

	private Logger logger = Logger.getLogger(SonyApiImpl.class.getName());
	private final SonyApiParam sonyApiParam;

	public SonyApiImpl(SonyApiParam sonyApiParam) {
		this.sonyApiParam = sonyApiParam;
	}

	@Override
	public String login() {
		try {
			String response = IOUtils.toString(
			        this.getClass().getResourceAsStream("/sony/LOGIN.xml"), "UTF-8");

			return response;
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public String getPlaybackStatus() {
		try {
			// Request the current time in the video
			VideoStreamStatus status = sonyApiParam.getVideoStreamWebService().getStatus();
			int currentTime_s = 0;
			String playState = "STOPPED";

			if (status != null) {
				currentTime_s = status.getCurrentTime_s();
				if (status.isPlaying()) {
					playState = "PLAYING";
				}
			}

			String splId = sonyApiParam.getSplUuid().toString();
			int remaingTime = (sonyApiParam.getMovieDurationSeconds() - currentTime_s) * 1000;
			int elapsedTime = currentTime_s * 1000;
			String cplId = sonyApiParam.getCplUuid().toString();

			String pattern = IOUtils.toString(
			        this.getClass().getResourceAsStream("/sony/GET_PLAYBACK.xml"), "UTF-8");
			String response = String.format(pattern, playState, splId, remaingTime, elapsedTime,
			        cplId);
			return response;
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public String getSplInfos() {
		try {
			// Request the current time in the video
			int currentTime_s = sonyApiParam.getVideoStreamWebService().getStatus()
			        .getCurrentTime_s();

			String title = sonyApiParam.getTitle();
			String duration = durationToSonyDuration(sonyApiParam.getMovieDurationSeconds());
			String offset = durationToSonyDuration(currentTime_s);
			String cplId = sonyApiParam.getCplUuid().toString();

			String pattern = IOUtils.toString(
			        this.getClass().getResourceAsStream("/sony/GET_SPLINFOS.xml"), "UTF-8");
			String response = String.format(pattern, title, cplId, duration, offset);
			return response;
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	private String durationToSonyDuration(int seconds) {
		if (seconds < 0) {
			return "00:00:00.000";
		}

		int second = seconds % 60;
		int minute = seconds / 60;
		int hour = minute / 60;
		minute = minute % 60;

		String h = hour > 9 ? Integer.toString(hour) : "0" + Integer.toString(hour);
		String m = minute > 9 ? Integer.toString(minute) : "0" + Integer.toString(minute);
		String s = second > 9 ? Integer.toString(second) : "0" + Integer.toString(second);

		return h + ':' + m + ':' + s + ".000";

	}

	@Override
	public String getCplInfos() {
		try {
			String title = sonyApiParam.getTitle();
			String pattern = IOUtils.toString(this.getClass().getResourceAsStream("/sony/cpl.xml"),
			        "UTF-8");
			String response = String.format(pattern, title);
			return response;
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public String getDeviceList() {
		try {
			String response = IOUtils.toString(
			        this.getClass().getResourceAsStream("/sony/GET_DEVICE_LIST.xml"), "UTF-8");

			return response;
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}
}
