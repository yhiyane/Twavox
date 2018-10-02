package com.cineapps.job.sync;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import org.apache.commons.id.uuid.UUID;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.cineapps.job.subtitle.SubtitleStartJob;
import com.cineapps.model.mediablock.MediablockSpl;
import com.cineapps.model.mediablock.MediablockState;
import com.cineapps.model.property.AuxiliaryContentProperty;
import com.cineapps.model.property.MediablockProperty;
import com.cineapps.model.property.PropertyLoader;
import com.cineapps.service.FtpService;
import com.cineapps.service.LicenseService;
import com.cineapps.service.MediablockService;
import com.cineapps.service.MonitoringService;
import com.cineapps.service.MultiStService;
import com.cineapps.service.ServicesHolder;
import com.cineapps.service.SubtitleService;
import com.cineapps.util.AuxiliaryContentUtils;
import com.cineapps.util.SchedulerUtils;
import com.cineapps.util.TimeUtils;
import com.dvidea.core.mediablock.plugin.PluginStatus;

/**
 * This class implements a quartz job, used to scan the mediablock status and
 * start the subtitle process on the play status.
 */
public class MediablockSynchronizerJob implements Job {

	private static final Logger logger = Logger.getLogger(MediablockSynchronizerJob.class);

	private MediablockService mediablockService;
	private SubtitleService subtitleService;
	private LicenseService licenseService;
	private FtpService ftpService;
	private MonitoringService monitoringService;
	private MultiStService multiStService;

	private final int NO_CPL_ID = 1;
	private final int NO_PLUGIN_ID = 2;
	private final int NO_CONNECTION_ID = 3;

	/**
	 * Start a MediablockSynchronizer job now.
	 * 
	 */
	public static void scheduleNow(String jobGroup) {
		logger.info("Start a MediablockSynchronizer job");

		// Create the job detail and trigger
		JobDetail job = JobBuilder.newJob(MediablockSynchronizerJob.class)
		        .withIdentity(UUID.randomUUID().toString(), jobGroup).build();
		Trigger trigger = TriggerBuilder.newTrigger().startAt(DateTime.now().toDate())
		        .withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

		// Schedule the job
		try {
			Scheduler scheduler = SchedulerUtils.getScheduler();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logger.error("Error when scheduling the mediablock synchronizer job");
			logger.error(e);
		}
	}

	/**
	 * Scan the mediablock waiting for the "Play" status. Then perform the
	 * process for broadcasting the subtitles.
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// Gets the services
		ServicesHolder servicesHolder = ServicesHolder.getInstance();
		mediablockService = servicesHolder.getMediablockService();
		subtitleService = servicesHolder.getSubtitleService();
		licenseService = servicesHolder.getLicenseService();
		ftpService = servicesHolder.getFtpService();
		monitoringService = servicesHolder.getMonitoringService();
		multiStService = servicesHolder.getMultiStService();

		// Gets the properties
		PropertyLoader propertyLoader = servicesHolder.getPropertyLoader();
		MediablockProperty mediablockProperty = propertyLoader.getMediablockProperty();
		AuxiliaryContentProperty auxiliaryContentProperty = propertyLoader
		        .getAuxiliaryContentProperty();

		// Try to connect to the mediablock
		boolean connected = mediablockService.connect(mediablockProperty.getMediablockIp(),
		        mediablockProperty.getMediablockPort(), mediablockProperty.getMediablockLogin(),
		        mediablockProperty.getMediablockPwd(), mediablockProperty.getMediablockType());

		if (connected) {
			// Gets the mediablock status
			PluginStatus pluginStatus = mediablockService.getPluginStatus();

			if (pluginStatus != null) {
				MediablockState mediablockState = mediablockService.getMediablockState();

				// Update the current state of the mediablock
				mediablockState.update(pluginStatus.getShowDuration(),
				        pluginStatus.getShowPosition(), pluginStatus.getElPosition(),
				        pluginStatus.getStatus());

				// Fire the subtitle process if can do it
				if (!subtitleService.isProcessing()
				        && canProcessSubtitles(mediablockService.getMediablockState(), pluginStatus)) {
					subtitleService.setProcessing(true);
					mediablockService.enableLog();
					// Monitoring
					monitoringService.monitorScanMediablock();

					MediablockSpl mediablockSpl = mediablockService.getCurrentPlaylist();
					if (mediablockSpl != null) {
						// Find the name of the feature with spls and cpls
						String playlistName = mediablockSpl.getFeatureName();
						mediablockState
						        .setPreshowDurationMillis(mediablockSpl.getPreshowDuration());
						logger.info("Found " + playlistName + " preshow "
						        + TimeUtils.stringFromMs(mediablockSpl.getPreshowDuration()));
						startSubtitleProcess(playlistName, auxiliaryContentProperty);
					} else {
						monitorError(NO_CPL_ID, "No FTR dcp found in the playlist");
					}
				}

				// Do these actions when the mediablock is stopped/paused
				if (subtitleService.isProcessing() && !mediablockState.isPlaying()) {
					// Stop the subtitle process
					subtitleService.stopProcess(true);
					// Stop the ftp process
					ftpService.notifyWantToStop();
					// Stop to retrieve the cpl list from the mediablock
					mediablockService.disposePlugin();
					// Clear the multi st current played subtitle list
					multiStService.clearCurrentPlayedSubtitles();
				}
			} else {
				monitorError(NO_PLUGIN_ID, "Connection error to the mediablock (no plugin)");
				// Stop the subtitle process
				if (subtitleService.isProcessing()) {
					subtitleService.stopProcess(false);
				}
			}
		} else {
			monitorError(NO_CONNECTION_ID, "Connection error to the mediablock (no connection)");
			// Stop the subtitle process
			if (subtitleService.isProcessing()) {
				subtitleService.stopProcess(false);
			}
		}
	}

	/**
	 * Returns true if we can begin to process of the subtitles, false
	 * otherwise.
	 * 
	 * @param pluginStatus
	 * @return
	 */
	private boolean canProcessSubtitles(MediablockState mediablockState, PluginStatus pluginStatus) {
		return mediablockState != null && pluginStatus.getStatus() == PluginStatus.STATUS_START
		        && mediablockState.isInShow();
	}

	/**
	 * Starts the process for the subtitles.
	 * 
	 * @param plName
	 *            the name of the playing dcp
	 * @param isACActivated
	 *            true if the AC module is activated in the license
	 */
	private void startSubtitleProcess(String plName,
	        AuxiliaryContentProperty auxiliaryContentProperty) {
		logger.info("Start the process for " + plName);
		if (licenseService.getLicense().isAuxContent()) {
			// Display the auxiliary content (poster and synopsis) if they are
			// present
			String auxContentDirUrl = auxiliaryContentProperty.getAuxiliaryContentDirUrl();
			String posterUrl = auxiliaryContentProperty.getPosterUrl();
			String synopsisUrl = auxiliaryContentProperty.getSynopsisUrl();
			AuxiliaryContentUtils.displayAuxiliaryContent(plName, auxContentDirUrl, posterUrl,
			        synopsisUrl);
		}

		subtitleService.setDcpName(plName);

		// Start the process for subtitles
		SubtitleStartJob.scheduleNow(plName);
	}

	/**
	 * Log and monitor an error message to the Acs-manager. If the same error
	 * occurs during the mediablock scan, we will monitor it after waiting a
	 * delay.
	 * 
	 * @param id
	 *            the id of the error message
	 * @param errorMessage
	 *            the error message to display
	 * @param mediablockProperty
	 */
	private void monitorError(int id, String errorMessage) {
		if (mediablockService.canMonitor(id)) {
			logger.error(errorMessage);
			monitoringService.monitorError("[Mediablock Scan]", errorMessage);
		}
	}

}
