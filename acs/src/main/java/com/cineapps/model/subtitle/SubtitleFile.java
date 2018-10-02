/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.subtitle;

import com.cineapps.util.TimeUtils;

/**
 * Handle the informations about a subtitle file
 */
public class SubtitleFile {

	private final String path;
	private final int editRateDurationFromStart;
	private final int editRateNumerator;
	private int relativeStartMillis = -1;

	public SubtitleFile(String path, int editRateDurationFromStart, int editRateNumerator) {
		this.path = path;
		this.editRateDurationFromStart = editRateDurationFromStart;
		this.editRateNumerator = editRateNumerator;
	}

	public String getPath() {
		return path;
	}

	public int getRelativeStartMillis() {
		if (relativeStartMillis == -1) {
			relativeStartMillis = TimeUtils.editRateToMillis(editRateDurationFromStart,
			        editRateNumerator);
		}
		return relativeStartMillis;
	}

}
