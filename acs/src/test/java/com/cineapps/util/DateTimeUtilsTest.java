/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class DateTimeUtilsTest {

	@Test
	public void testParse() {
		int hour = 10;
		int minute = 20;
		int second = 45;
		int millis = 355;

		String format = "HH:mm:ss:SSS";
		String timeStr = Integer.toString(hour) + ':' + Integer.toString(minute) + ':'
		        + Integer.toString(second) + ':' + Integer.toString(millis);

		DateTime time = DateTimeUtils.parse(timeStr, format);
		Assert.assertEquals(hour, time.getHourOfDay());
		Assert.assertEquals(minute, time.getMinuteOfHour());
		Assert.assertEquals(second, time.getSecondOfMinute());
		Assert.assertEquals(millis, time.getMillisOfSecond());
	}
}
