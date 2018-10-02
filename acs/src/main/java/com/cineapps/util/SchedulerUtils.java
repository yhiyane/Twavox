/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerUtils {

	private static final Logger logger = Logger.getLogger(SchedulerUtils.class);
	private static Scheduler scheduler;

	// @formatter:off
	/**
	 * Returns the default quartz scheduler or null if any error occurred.
	 * 
	 * @return
	 */
	// @formatter:on
	public static Scheduler getScheduler() {
		if (scheduler == null) {
			try {
				scheduler = StdSchedulerFactory.getDefaultScheduler();
				scheduler.start();
			} catch (SchedulerException e) {
				logger.error("Cannot initialize the scheduler");
				logger.error(e);
			}
		}
		return scheduler;
	}

	protected static void setScheduler(Scheduler scheduler) {
		SchedulerUtils.scheduler = scheduler;
	}
}
