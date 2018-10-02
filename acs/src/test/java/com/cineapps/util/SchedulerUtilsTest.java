/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Test;
import org.quartz.Scheduler;

public class SchedulerUtilsTest {

	@Test
	public void testGetScheduler() {
		SchedulerUtils.setScheduler(null);
		Scheduler scheduler = SchedulerUtils.getScheduler();
		Assert.assertNotNull(scheduler);
	}

	@Test
	public void testGetExistingScheduler() {
		Scheduler expected = mock(Scheduler.class);
		SchedulerUtils.setScheduler(expected);

		Scheduler actual = SchedulerUtils.getScheduler();
		Assert.assertEquals(expected, actual);
	}
}
