/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import org.junit.Assert;
import org.junit.Test;

public class TimeUtilsTest {

	@Test
	public void testMsFromString() {
		Assert.assertEquals(256, TimeUtils.msFromString("00:00:00:256"));
		Assert.assertEquals(1256, TimeUtils.msFromString("00:00:01:256"));
		Assert.assertEquals(61256, TimeUtils.msFromString("00:01:01:256"));
		Assert.assertEquals(3661256, TimeUtils.msFromString("01:01:01:256"));
	}

	@Test
	public void testEditRateToMillis() {
		Assert.assertEquals(10000, TimeUtils.editRateToMillis(240, 24));
		Assert.assertEquals(12083, TimeUtils.editRateToMillis(290, 24));
		Assert.assertEquals(15500, TimeUtils.editRateToMillis(372, 24));

		Assert.assertEquals(5000, TimeUtils.editRateToMillis(240, 48));
		Assert.assertEquals(6041, TimeUtils.editRateToMillis(290, 48));
		Assert.assertEquals(7750, TimeUtils.editRateToMillis(372, 48));
	}

	@Test
	public void testStringFromMs() {
		Assert.assertEquals("0:0:0:250", TimeUtils.stringFromMs(250));
		Assert.assertEquals("0:0:20:250", TimeUtils.stringFromMs(20250));
		Assert.assertEquals("0:1:20:250", TimeUtils.stringFromMs(80250));
		Assert.assertEquals("1:17:20:250", TimeUtils.stringFromMs(4640250));
		Assert.assertEquals("0:16:8:750", TimeUtils.stringFromMs(968750));
	}
}
