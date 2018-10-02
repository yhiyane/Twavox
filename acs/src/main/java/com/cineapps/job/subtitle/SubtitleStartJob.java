/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.job.subtitle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.cineapps.manager.CplFilesManager;
import com.cineapps.model.cpl.CompositionPlaylist;
import com.cineapps.model.mediablock.MediablockState;
import com.cineapps.model.property.PropertyLoader;
import com.cineapps.model.property.SubtitleProperty;
import com.cineapps.model.subtitle.SubtitleInfo;
import com.cineapps.service.FtpService;
import com.cineapps.service.LicenseService;
import com.cineapps.service.MediablockService;
import com.cineapps.service.MonitoringService;
import com.cineapps.service.MultiStService;
import com.cineapps.service.ServicesHolder;
import com.cineapps.service.SubtitleService;
import com.cineapps.util.SchedulerUtils;
import com.cineapps.util.SubtitleUtils;

/**
 * This class implements a quartz job, used to prepare all the needed
 * requirements for the subtitle streaming (download the subtitle files for
 * example)
 */
public class SubtitleStartJob implements Job {

	private static final Logger logger = Logger.getLogger(SubtitleStartJob.class);
	private SubtitleService subtitleService;
	private MediablockService mediablockService;
	private FtpService ftpService;
	private MonitoringService monitoringService;
	private LicenseService licenseService;
	private MultiStService multiStService;

	/**
	 * Schedules a SubtitleStartJob job.
	 * 
	 * @param plName
	 *            the name of the playing dcp
	 */
	public static void scheduleNow(String plName) {

		// Create the data map
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("plName", plName);

		// Create the job detail and trigger
		String jobGroup = ServicesHolder.getInstance().getSubtitleService().getJobGroup();
		JobDetail job = JobBuilder.newJob(SubtitleStartJob.class)
		        .withIdentity(UUID.randomUUID().toString(), jobGroup).usingJobData(jobDataMap)
		        .build();
		Trigger trigger = TriggerBuilder.newTrigger().startAt(DateTime.now().toDate()).build();

		// Schedule the job
		try {
			Scheduler scheduler = SchedulerUtils.getScheduler();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logger.error(e);
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		// Data map
		String plName = jobDataMap.getString("plName");

		// Gets the services
		ServicesHolder servicesHolder = ServicesHolder.getInstance();
		mediablockService = servicesHolder.getMediablockService();
		subtitleService = servicesHolder.getSubtitleService();
		ftpService = servicesHolder.getFtpService();
		monitoringService = servicesHolder.getMonitoringService();
		licenseService = servicesHolder.getLicenseService();
		multiStService = servicesHolder.getMultiStService();

		// Gets the properties
		PropertyLoader propertyLoader = servicesHolder.getPropertyLoader();
		SubtitleProperty subtitleProperty = propertyLoader.getSubtitleProperty();

		List<SubtitleInfo> processed = new ArrayList<>();

		try {
			// Download the cpl files from the ftp
			CplFilesManager cplFilesManager = ftpService
			        .downloadIfNeeded(plName, monitoringService);

			if (cplFilesManager != null && subtitleService.isProcessing()) {
				processed = playedSubtitleFiles(cplFilesManager, plName, subtitleProperty);

				if (!processed.isEmpty()) {
					// Update the list of current played subtitle list for the
					// web service
					multiStService.setCurrentPlayedSubtitles(processed);

				} else {
					logger.info("No subtitle to process");
				}
			} else if (cplFilesManager == null) {
				logger.info("No subtitle file found");
			}
		} catch (Exception e) {
			logger.error(e);
			monitoringService.monitorException("Error during subtitle process", e);
		} finally {
			// Monitoring
			if (!processed.isEmpty()) {
				monitoringService.monitorPlay(plName, processed);
			} else {
				monitoringService.monitorPlayEmpty(plName);
			}
		}
	}

	/**
	 * Launch the process for subtitle files and returns the list of played
	 * subtitles.
	 * 
	 * @param cplFilesManager
	 *            the manager of the cpl list
	 * @param plName
	 *            the title of the playing dcp
	 * @param subtitleProperty
	 * @return
	 */
	private List<SubtitleInfo> playedSubtitleFiles(CplFilesManager cplFilesManager, String plName,
	        SubtitleProperty subtitleProperty) {

		String directoryPath = cplFilesManager.getDirectoryPath();
		List<CompositionPlaylist> cpls = cplFilesManager.getCpls();
		CompositionPlaylist currentCpl = cplFilesManager.findByName(plName);

		List<String> freeLanguages = subtitleProperty.getSubtitleFreeLanguages();
		int defaultPort = subtitleProperty.getSubtitleDefaultPort();

		if (currentCpl == null) {
			logger.error("No cpl file found for " + plName);
			return Collections.emptyList();
		}

		boolean isMultiStActivated = licenseService.getLicense().getMultiStNbCoins() >= 0;
		List<SubtitleInfo> subtitleInfos = SubtitleUtils.prepareSubtitleInfo(directoryPath, cpls,
		        currentCpl, isMultiStActivated, freeLanguages, defaultPort);
		MediablockState mediablockState = mediablockService.getMediablockState();

		// Process subtitles for the beginning of the show
		if (!subtitleInfos.isEmpty()) {
			for (SubtitleInfo subtitleInfo : subtitleInfos) {
				DateTime datetime = DateTime.now().plusMillis(
				        (int) mediablockState.getMovieStartMillis());
				scheduleProcessJob(subtitleInfo, datetime);
			}
		}
		return subtitleInfos;
	}

	/**
	 * Schedules a process job.
	 * 
	 * @param subtitleInfo
	 *            the informations about the subtitle to process.
	 * @param dateTime
	 *            the date of the job execution.
	 */
	private void scheduleProcessJob(SubtitleInfo subtitleInfo, DateTime dateTime) {
		logger.info("Process the " + subtitleInfo.getLang() + " on port " + subtitleInfo.getPort()
		        + " with " + subtitleInfo.getDcpTitle());

		// Warn the service that one process will fire
		subtitleService.warnProcessFire(subtitleInfo);

		SubtitleProcessJob.scheduleFor(dateTime, subtitleInfo, null, 0, 0, 0, -1, 0);
	}

}
