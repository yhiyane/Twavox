/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.matchers.GroupMatcher;

import com.cineapps.job.multist.MultiStWebServiceJob;
import com.cineapps.model.subtitle.SubtitleInfo;
import com.cineapps.util.SchedulerUtils;

public class MultiStService implements IService {

	private final Logger logger = Logger.getLogger(MultiStService.class);
	private static final String JOB_GROUP = "MultiStJobGroup";
	private List<SubtitleInfo> currentPlayedSubtitles = new ArrayList<>();
	private UUID sessionUuid;

	public MultiStService() {
	}

	@Override
	public void startProcess() {
		// Start the MultiSt jobs now.
		try {
			MultiStWebServiceJob.scheduleNow(getJobGroup());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void stopProcess() {
		try {
			// Removes all scheduled jobs with jobGroup
			Scheduler scheduler = SchedulerUtils.getScheduler();
			List<JobKey> mediablockJobKeys;
			mediablockJobKeys = new ArrayList<>(scheduler.getJobKeys(GroupMatcher
			        .jobGroupEquals(getJobGroup())));
			scheduler.deleteJobs(mediablockJobKeys);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public String getJobGroup() {
		return JOB_GROUP;
	}

	public List<SubtitleInfo> getCurrentPlayedSubtitles() {
		return currentPlayedSubtitles;
	}

	public void setCurrentPlayedSubtitles(List<SubtitleInfo> currentPlayedSubtitles) {
		this.currentPlayedSubtitles = currentPlayedSubtitles;
		this.sessionUuid = UUID.randomUUID();
	}

	public void clearCurrentPlayedSubtitles() {
		this.currentPlayedSubtitles = new ArrayList<>();
		this.sessionUuid = null;
	}

	public UUID getSessionUuid() {
		return sessionUuid;
	}
}
