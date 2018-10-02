package com.cineapps.job.sync;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

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

import com.cineapps.service.LicenseService;
import com.cineapps.service.ServicesHolder;
import com.cineapps.util.SchedulerUtils;

/**
 * This class implements a quartz job, used to schedule a repetitive task for
 * checking the validity of the license.
 */
public class LicenseSynchronizerJob implements Job {

	private static final Logger logger = Logger.getLogger(LicenseSynchronizerJob.class);

	/**
	 * Schedule every day a job for checking the validity of the license.
	 * 
	 * @param licenseFileUrl
	 *            the path to the license file
	 */
	public static void scheduleEveryDay(String licenseFileUrl) {
		// Create the job detail and trigger
		JobDataMap map = new JobDataMap();
		map.put("licenseFileUrl", licenseFileUrl);
		JobDetail job = JobBuilder.newJob(LicenseSynchronizerJob.class).usingJobData(map).build();
		Trigger trigger = TriggerBuilder.newTrigger().startAt(DateTime.now().plusDays(1).toDate())
		        .withSchedule(simpleSchedule().withIntervalInHours(24).repeatForever()).build();

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
		String licenseFileUrl = (String) jobDataMap.get("licenseFileUrl");

		// Reload the license file
		ServicesHolder servicesHolder = ServicesHolder.getInstance();
		LicenseService licenseService = servicesHolder.getLicenseService();
		licenseService.loadLicense(licenseFileUrl);

		// If the license is not valid, stop all services.
		if (!licenseService.isLicenseValid()) {
			servicesHolder.stop();
			logger.error("License is invalid");
		}
	}

}
