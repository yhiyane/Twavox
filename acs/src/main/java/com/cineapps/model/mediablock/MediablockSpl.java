/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.mediablock;

/**
 * Handles the informations of the spls (playlist)
 */
public class MediablockSpl {

	private final String featureName;
	private final int preshowDuration;

	public MediablockSpl(String featureName, int preshowDuration) {
		this.featureName = featureName;
		this.preshowDuration = preshowDuration;
	}

	public String getFeatureName() {
		return featureName;
	}

	public int getPreshowDuration() {
		return preshowDuration;
	}
}
