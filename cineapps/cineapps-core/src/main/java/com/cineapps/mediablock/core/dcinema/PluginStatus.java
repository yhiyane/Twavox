/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.dcinema;

import java.util.UUID;

public class PluginStatus {

	public static final int OK = 0;
	public static final int ERROR = 1;

	public static final int STATUS_START = 0;
	public static final int STATUS_STOP = 1;

	private int error = 0;
	private int status;
	private UUID plId;
	private int showDuration;
	private int showPosition;
	private int elPosition;

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UUID getPlId() {
		return plId;
	}

	public void setPlId(UUID plId) {
		this.plId = plId;
	}

	public int getShowDuration() {
		return showDuration;
	}

	public void setShowDuration(int showDuration) {
		this.showDuration = showDuration;
	}

	public int getShowPosition() {
		return showPosition;
	}

	public void setShowPosition(int showPosition) {
		this.showPosition = showPosition;
	}

	public int getElPosition() {
		return elPosition;
	}

	public void setElPosition(int elPosition) {
		this.elPosition = elPosition;
	}

}
