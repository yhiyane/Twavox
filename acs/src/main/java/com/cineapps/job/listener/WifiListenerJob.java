/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.job.listener;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.cineapps.service.MonitoringService;
import com.cineapps.service.ServicesHolder;
import com.cineapps.util.SchedulerUtils;

public class WifiListenerJob implements Job {

	private static final Logger logger = Logger.getLogger(WifiListenerJob.class);
	private final String WIFI_IP_ADDRESS = "192.168.0.1";
	private final int PING_TIMEOUT = 1000;

	/**
	 * Schedule every day a job for checking the validity of the license.
	 * 
	 * @param licenseFileUrl
	 *            the path to the license file
	 */
	public static void run() {
		// Create the job detail and trigger
		JobDetail job = JobBuilder.newJob(WifiListenerJob.class).build();
		Trigger trigger = TriggerBuilder.newTrigger()
		        .withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

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
		// Ping the wifi ip address every 5 secondes
		try {
			InetAddress inet = InetAddress.getByName(WIFI_IP_ADDRESS);
			boolean isReachable = inet.isReachable(PING_TIMEOUT);

			ServicesHolder servicesHolder = ServicesHolder.getInstance();
			MonitoringService monitoringService = servicesHolder.getMonitoringService();
			monitoringService.monitorWifiReachable(isReachable);
		} catch (IOException e) {
			logger.error(e);
		}

	}

}
