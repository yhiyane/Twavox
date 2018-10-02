/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtils {

	// @formatter:off
	/**
	 * Parses the date in string.
	 * 
	 * @param time
	 *            : the date in string
	 * @param format
	 *            : the format of the date
	 * @return
	 */
	// @formatter:on
	public static DateTime parse(String time, String format) {
		DateTimeFormatter formatter = new DateTimeFormatter(null, DateTimeFormat.forPattern(format)
		        .getParser());
		return formatter.parseDateTime(time);
	}
}
