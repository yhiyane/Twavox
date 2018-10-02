/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.job.subtitle;

import org.apache.commons.id.uuid.UUID;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
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

import com.cineapps.service.BroadcastService;
import com.cineapps.service.ServicesHolder;
import com.cineapps.util.SchedulerUtils;

/**
 * This class implements a quartz job, used to send the packet to devices
 * through the wi-fi.
 */
public class SubtitleSendJob implements Job {

	private static final Logger logger = Logger.getLogger(SubtitleSendJob.class);

	/**
	 * Schedules a SubtitleSendJob job.
	 * 
	 * @param dateTime
	 * @param port
	 * @param text
	 * @param spotNumber
	 * @param textAppearanceMillis
	 * @param textDurationMillis
	 * @param sendNumber
	 * @param sendTotal
	 */
	public static void scheduleFor(DateTime dateTime, int port, String text, int spotNumber,
	        long textAppearanceMillis, long textDurationMillis, int sendNumber, int sendTotal) {

		// Create the data map
		JobDataMap map = new JobDataMap();
		map.put("text", text);
		map.put("port", port);
		map.put("spotNumber", spotNumber);
		map.put("textAppearanceMillis", textAppearanceMillis);
		map.put("textDurationMillis", textDurationMillis);
		map.put("sendNumber", sendNumber);
		map.put("sendTotal", sendTotal);

		// Create the job detail and trigger
		String jobGroup = ServicesHolder.getInstance().getSubtitleService().getJobGroup();
		JobDetail job = JobBuilder.newJob(SubtitleSendJob.class)
		        .withIdentity(UUID.randomUUID().toString(), jobGroup).usingJobData(map).build();
		Trigger trigger = TriggerBuilder.newTrigger()
		        .startAt(dateTime.isBeforeNow() ? DateTime.now().toDate() : dateTime.toDate())
		        .build();

		// Schedule the job
		try {
			Scheduler scheduler = SchedulerUtils.getScheduler();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logger.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		int port = jobDataMap.getInt("port");

		// Build the json packet to send
		JSONObject content = new JSONObject();
		content.put("text", jobDataMap.get("text"));
		content.put("spotNumber", jobDataMap.get("spotNumber"));
		content.put("textAppearanceMillis", jobDataMap.get("textAppearanceMillis"));
		content.put("textDurationMillis", jobDataMap.get("textDurationMillis"));
		content.put("sendNumber", jobDataMap.get("sendNumber"));
		content.put("sendTotal", jobDataMap.get("sendTotal"));

		// Send the packet
		BroadcastService broadcastService = ServicesHolder.getInstance().getBroadcastService();
		broadcastService.broadcast(content.toJSONString(), port);
	}

	/**
	 * Send a "Stop" notification to devices. When this message is received, the
	 * device stops to display the received packets.
	 * 
	 * @param broadcastService
	 * @param port
	 */
	public static void sendStop(final BroadcastService broadcastService, final int port) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String content = "STOP";

				for (int i = 0; i < SubtitleProcessJob.NB_SEND; i++) {
					broadcastService.broadcast(content, port);
					try {
						Thread.sleep(SubtitleProcessJob.MS_BETWEEN_SENDING);
					} catch (InterruptedException e) {
						logger.error(e);
					}
				}
			}
		}).start();

	}
}
