/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;

import com.cineapps.job.listener.WifiListenerJob;
import com.cineapps.util.SchedulerUtils;

public class WifiListenerService implements IService {

	private static final String JOB_GROUP = "WifiListenerJobGroup";
	private static final Logger logger = Logger.getLogger(WifiListenerService.class);

	@Override
	public void startProcess() {
		WifiListenerJob.run();
	}

	@Override
	public void stopProcess() {
		// Removes all schedules related jobs
		Scheduler scheduler = SchedulerUtils.getScheduler();
		List<JobKey> mediablockJobKeys;
		try {
			mediablockJobKeys = new ArrayList<>(scheduler.getJobKeys(GroupMatcher
			        .jobGroupEquals(getJobGroup())));
			scheduler.deleteJobs(mediablockJobKeys);
		} catch (SchedulerException e) {
			logger.error(e);
		}
	}

	@Override
	public String getJobGroup() {
		return JOB_GROUP;
	}
}
