/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

public class TimeUtils {

	// @formatter:off
	/**
	 * Returns the number of milliseconds from string in format "HH:mm:ss:SSS" 
	 * Ex : "00:01:15:256" returns 75256
	 * 
	 * @param time
	 * @return
	 */
	// @formatter:on
	public static int msFromString(String time) {
		String[] split = time.split(":");
		int hour = Integer.parseInt(split[0]);
		int min = Integer.parseInt(split[1]);
		int sec = Integer.parseInt(split[2]);
		int ms = Integer.parseInt(split[3]);
		return ((hour * 60 + min) * 60 + sec) * 1000 + ms;
	}

	// @formatter:off
	/**
	 * Returns the string representation of a number of milliseconds 
	 * in the format HH:mm:ss:SSS.
	 * 
	 * @param millis
	 * @return
	 */
	// @formatter:on
	public static String stringFromMs(long millis) {
		long second = millis / 1000;
		long ms = millis % 1000;
		long minute = second / 60;
		second = second % 60;
		long hour = minute / 60;
		minute = minute % 60;
		return hour + ":" + minute + ":" + second + ":" + ms;
	}

	// @formatter:off
	/**
	 * Converts the edit duration to milliseconds. 
	 * 
	 * Ex : 
	 * editRateDuration = 240 images 
	 * editRateNumerator = 24 images per second 
	 * the method should return 10000 ms
	 * 
	 * @param editRateDuration
	 *            : the duration in the edit rate unit
	 * @param editRateNumerator
	 *            : the numerator of the edit rate unit
	 * @return
	 */
	// @formatter:on
	public static int editRateToMillis(int editRateDuration, int editRateNumerator) {
		int sign = editRateDuration < 0 ? -1 : 1;
		int absEditRateDuration = Math.abs(editRateDuration);
		int nbSeconds = absEditRateDuration / editRateNumerator;
		int nbRestImage = absEditRateDuration % editRateNumerator;
		int millis = (int) (nbSeconds * 1000 + (nbRestImage / (float) editRateNumerator) * 1000);
		return sign * millis;
	}
}
