/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.core;

import org.junit.Assert;
import org.junit.Test;

import com.cineapps.mediablock.core.dcinema.Rate;

public class TestRate {
	@Test
	public void testRateFromString() {
		String rateStr = "24 1";
		Rate rate = new Rate(rateStr);
		Assert.assertEquals(1, rate.getDenominator());
		Assert.assertEquals(24, rate.getNumerator());
	}
}
