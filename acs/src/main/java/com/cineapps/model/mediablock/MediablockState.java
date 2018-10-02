/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.mediablock;

import com.dvidea.core.mediablock.plugin.PluginStatus;

/**
 * Object used to store the state of the mediablock.
 */
public class MediablockState {

	private long preshowDuration_ms;
	private int showDuration_s;
	private int showPosition_s;
	private int elPosition_s;
	private int playback;
	private long request_ms;
	private final Object lock = new Object();

	/**
	 * Updates the state of the mediablock. The operation is synchronized.
	 * 
	 */
	public void update(int showDuration, int showPosition, int elPosition, int playback) {
		synchronized (lock) {
			this.showDuration_s = showDuration;
			this.showPosition_s = showPosition;
			this.elPosition_s = elPosition;
			this.playback = playback;
			this.request_ms = System.currentTimeMillis();
		}
	}

	public void setPreshowDurationMillis(long preshowDuration) {
		this.preshowDuration_ms = preshowDuration;
	}

	public long getPreshowDurationMillis() {
		synchronized (lock) {
			return preshowDuration_ms;
		}
	}

	/**
	 * Returns the milliseconds to wait until the start of the movie. If the
	 * playlist has already started, returns 0.
	 * 
	 * @return
	 */
	public long getMovieStartMillis() {
		synchronized (lock) {
			if (showPosition_s * 1000 < preshowDuration_ms) {
				return preshowDuration_ms - showPosition_s * 1000 + System.currentTimeMillis()
				        - request_ms;
			}
			return 0;
		}
	}

	/**
	 * Returns the current position in the element in ms.
	 * 
	 * @return
	 */
	public long getCurrentPositionMillis() {
		synchronized (lock) {
			long currentPositionMsInElement = elPosition_s * 1000;
			return currentPositionMsInElement + System.currentTimeMillis() - request_ms;
		}
	}

	/**
	 * Returns true if the current position is in the show, false otherwise.
	 */
	public boolean isInShow() {
		return showPosition_s > 0 && showPosition_s <= showDuration_s;
	}

	/**
	 * Returns true if the mediablock is playing, false otherwise.
	 */
	public boolean isPlaying() {
		return PluginStatus.STATUS_START == playback;
	}
}
