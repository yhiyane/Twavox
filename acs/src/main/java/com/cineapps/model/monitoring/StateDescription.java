/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.monitoring;

/**
 * Mapping between the state and the id message to display. The state
 * ('processing', 'playing' or 'error') is used to select the right css class
 * for the UI. The id are defined in the Acs-Manager project.
 */
public enum StateDescription {

	// Code message : See acs-manager/resources/messages.json
	STOP("processing", "1"), SCAN_MEDIABLOCK("processing", "2"), SCAN_LIBRARY("processing", "3"), DOWNLOAD(
	        "processing", "4"), PLAY("playing", "5-%d"), PLAY_EMPTY("playing", "6"), ERROR("error",
	        "7-%s");

	private final String state;
	private final String description;

	private StateDescription(String state, String description) {
		this.state = state;
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public String getDescription() {
		return description;
	}
}
