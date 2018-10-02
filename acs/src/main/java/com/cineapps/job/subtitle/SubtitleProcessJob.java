/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.job.subtitle;

import java.util.List;

import org.apache.commons.id.uuid.UUID;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.cineapps.model.mediablock.MediablockState;
import com.cineapps.model.property.PropertyLoader;
import com.cineapps.model.property.SubtitleProperty;
import com.cineapps.model.subtitle.SubtitleFile;
import com.cineapps.model.subtitle.SubtitleInfo;
import com.cineapps.service.MediablockService;
import com.cineapps.service.ServicesHolder;
import com.cineapps.service.SubtitleService;
import com.cineapps.util.SchedulerUtils;
import com.cineapps.util.SubtitleUtils;
import com.cineapps.util.TimeUtils;

/**
 * This class implements a quartz job, used to prepare the packets to send to
 * the device application.
 */
public class SubtitleProcessJob implements Job {

	private static final Logger logger = Logger.getLogger(SubtitleProcessJob.class);

	// Number of packets to send for the same subtitle text
	public static final int NB_SEND = 5;
	// The duration in millisecond between two consecutive sending of the same
	// subtitle text
	public static final int MS_BETWEEN_SENDING = 1000;
	// The text to display on device when waiting for a subtitle text
	private static final String WAITING_TEXT = "<Font Color=\"FFFFFFFF\"><Subtitle><Text HAlign=\"%s\">---</Text></Subtitle></Font>";
	// The array of the position for the waiting text
	private static final String[] WAITING_TEXT_POSITION = { "left", "center", "right" };
	// The duration in millisecond to display the waiting text on device
	private static final int WAITING_TEXT_DURATION_MILLIS = 2000;
	// The minimum duration in millisecond to display or not the waiting text
	private static final int WAITING_MIN_MILLIS = 1 * WAITING_TEXT_DURATION_MILLIS;

	private MediablockService mediablockService;
	private SubtitleService subtitleService;

	/**
	 * Schedules a SubtitleProcessJob job.
	 * 
	 * @param dateTime
	 *            the date of the execution
	 * @param subtitleInfo
	 *            the information about the processed subtitle
	 * @param subtitles
	 *            the list of subtitles text
	 * @param offsetMillisIn
	 *            the milliseconds between the first reel and the current reel
	 * @param offsetSpotNumber
	 *            the spotNumber of the last sent text
	 * @param lastMillisOut
	 *            the milliseconds between the beginning and the last sent
	 *            subtitle
	 * @param extraSpotNumber
	 *            the spotNumber of the last sent waiting text
	 * @param waitingArrayOffset
	 *            the position in the waiting text position array
	 */
	public static void scheduleFor(DateTime dateTime, SubtitleInfo subtitleInfo,
	        List<Node> subtitles, int offsetMillisIn, int offsetSpotNumber, long lastMillisOut,
	        int extraSpotNumber, int waitingArrayOffset) {

		// Create the data map
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("subtitleInfo", subtitleInfo);
		jobDataMap.put("subtitles", subtitles);
		jobDataMap.put("offsetMillisIn", offsetMillisIn);
		jobDataMap.put("offsetSpotNumber", offsetSpotNumber);
		jobDataMap.put("lastMillisOut", lastMillisOut);
		jobDataMap.put("extraSpotNumber", extraSpotNumber);
		jobDataMap.put("waitingArrayOffset", waitingArrayOffset);

		// Create the job detail and trigger
		String jobGroup = ServicesHolder.getInstance().getSubtitleService().getJobGroup();
		JobDetail job = JobBuilder.newJob(SubtitleProcessJob.class)
		        .withIdentity(UUID.randomUUID().toString(), jobGroup).usingJobData(jobDataMap)
		        .build();
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
		// Data map
		SubtitleInfo subtitleInfo = (SubtitleInfo) jobDataMap.get("subtitleInfo");
		List<Node> subtitles = (List<Node>) jobDataMap.get("subtitles");
		int offsetMillisIn = jobDataMap.getInt("offsetMillisIn");
		int offsetSpotNumber = jobDataMap.getInt("offsetSpotNumber");
		long lastMillisOut = jobDataMap.getLong("lastMillisOut");
		int extraSpotNumber = jobDataMap.getInt("extraSpotNumber");
		int waitingArrayOffset = jobDataMap.getInt("waitingArrayOffset");

		// Gets the services
		ServicesHolder servicesHolder = ServicesHolder.getInstance();
		mediablockService = servicesHolder.getMediablockService();
		subtitleService = servicesHolder.getSubtitleService();

		// Gets the properties
		PropertyLoader propertyLoader = servicesHolder.getPropertyLoader();
		SubtitleProperty subtitleProperty = propertyLoader.getSubtitleProperty();

		// List of nodes of subtitles
		List<SubtitleFile> subtitleFiles = subtitleInfo.getSubtitleFiles();
		if (subtitles == null) {
			subtitles = SubtitleUtils.extractSubtitles(subtitleFiles.remove(0).getPath());
		}

		// Prepares the variables
		int count = 0;
		long firstMillisIn = -1;
		long millisIn;
		long millisOut = 0;
		long textDurationMillis;
		long beginSendingMillis;
		MediablockState mediablockState = mediablockService.getMediablockState();
		int spotNumber = 0;
		int nbProcess = subtitleProperty.getSubtitleNbProcessing();
		boolean nextIsWaitingText = false;

		// Processing
		// While we can send subtitles
		while (!subtitles.isEmpty() && count < nbProcess) {
			// The content of the packet to send is the node of the subtitle
			// text
			// The format is specified by the SMPTE standards
			Node subtitle = subtitles.remove(0);
			Node origineNode = subtitle.cloneNode(true);
			String text = SubtitleUtils.nodeToString(subtitleService.getTransformer(), subtitle);

			// Find the "Subtitle" sub-node to extract the time stamp
			// informations
			if (!subtitle.getNodeName().equals("Subtitle")) {
				subtitle = ((Element) subtitle).getElementsByTagName("Subtitle").item(0);
			}

			spotNumber = Integer.parseInt(((Element) subtitle).getAttribute("SpotNumber"));

			// Convert time stamp attributes in Ms (the time stamp is relative
			// to the reel, not to the beginning of the show)
			// So add the offset and the preshow duration to be relative to the
			// beginning of the show
			millisIn = subtitleInfo.getPreMovieDurationMillis() + offsetMillisIn
			        + TimeUtils.msFromString(((Element) subtitle).getAttribute("TimeIn"));
			millisOut = subtitleInfo.getPreMovieDurationMillis() + offsetMillisIn
			        + TimeUtils.msFromString(((Element) subtitle).getAttribute("TimeOut"));

			// Calculate the text duration in Ms
			textDurationMillis = millisOut - millisIn;
			// Calculate the beginning of the sending
			beginSendingMillis = millisIn - NB_SEND * 1000;

			// Initializes the firstMillisIn
			if (firstMillisIn == -1) {
				firstMillisIn = millisIn;
			}

			long currentPositionMsInElement = mediablockState.getCurrentPositionMillis();

			// Send the waiting texts if can do it
			long endSend = millisIn - MS_BETWEEN_SENDING - NB_SEND * 1000;

			if ((millisIn - lastMillisOut - 2 * MS_BETWEEN_SENDING) > WAITING_MIN_MILLIS
			        && currentPositionMsInElement < endSend) {
				long startSend = lastMillisOut + MS_BETWEEN_SENDING - NB_SEND * 1000;

				while (startSend < endSend && count < nbProcess) {
					String waitingText = String
					        .format(WAITING_TEXT, WAITING_TEXT_POSITION[waitingArrayOffset
					                % WAITING_TEXT_POSITION.length]);
					if (scheduleSendText(subtitleInfo.getPort(), startSend,
					        currentPositionMsInElement, waitingText, extraSpotNumber,
					        WAITING_TEXT_DURATION_MILLIS, subtitleProperty.getSubtitleDelay())) {
						extraSpotNumber--;
						waitingArrayOffset++;
						count++;

						// Update the time stamp of the first sent subtitle
						long firstWaitingStart = startSend + NB_SEND * 1000;
						if (firstMillisIn > firstWaitingStart) {
							firstMillisIn = firstWaitingStart;
						}
					}

					startSend += WAITING_TEXT_DURATION_MILLIS;
				}

				if (count == nbProcess) {
					// reinsert the subtitle node in the beginning
					subtitles.add(0, origineNode);
					// update the time stamp
					millisOut = startSend + NB_SEND * 1000;
					// here means the next text may be waiting texts
					nextIsWaitingText = true;
				}
			}

			if (count < nbProcess) {
				// Refresh this value to be more precise
				currentPositionMsInElement = mediablockState.getCurrentPositionMillis();

				// Send the subtitle text
				if (scheduleSendText(subtitleInfo.getPort(), beginSendingMillis,
				        currentPositionMsInElement, text, offsetSpotNumber + spotNumber,
				        textDurationMillis, subtitleProperty.getSubtitleDelay())) {
					count++;
				}
			}

			// updates the last millisOut
			lastMillisOut = millisOut;
		}

		// Load the next subtitles if needed
		if (subtitles.isEmpty() && !subtitleFiles.isEmpty()) {
			// The subtitle file comes with its relative start time
			SubtitleFile subtitleFile = subtitleFiles.remove(0);
			offsetMillisIn = subtitleFile.getRelativeStartMillis();
			subtitles.addAll(SubtitleUtils.extractSubtitles(subtitleFile.getPath()));
			offsetSpotNumber += spotNumber;
		}
		if (!subtitles.isEmpty()) {
			// Recursive call
			DateTime nextProcessDate = DateTime.now().plusMillis(
			        (int) (firstMillisIn - mediablockState.getCurrentPositionMillis()));

			scheduleFor(nextProcessDate, subtitleInfo, subtitles, offsetMillisIn, offsetSpotNumber,
			        lastMillisOut, extraSpotNumber, nextIsWaitingText ? waitingArrayOffset : 0);
		}
	}

	/**
	 * Send the text several times to be sure it will be received.
	 * 
	 * @param port
	 * @param beginSendingMillis
	 * @param currentPositionMsInShow
	 * @param text
	 * @param spotNumber
	 * @param textDurationMillis
	 * @param text_delay_ms
	 * @return true if the text is sent, false otherwise.
	 */
	private static boolean scheduleSendText(int port, long beginSendingMillis,
	        long currentPositionMsInShow, String text, int spotNumber, long textDurationMillis,
	        int text_delay_ms) {

		// Send the text only if the beginning_sending_ms is after the
		// current position in the show
		if (text != null && beginSendingMillis > currentPositionMsInShow) {
			// Send the subtitle
			for (int i = 0; i < NB_SEND; i++) {
				long sendTimeMsFromCurrentPosition = beginSendingMillis - currentPositionMsInShow
				        + i * MS_BETWEEN_SENDING;
				// The Ms between the sending time and the text appearance
				// time
				long textAppearanceFromSendingTime = (NB_SEND - i) * MS_BETWEEN_SENDING
				        + text_delay_ms;

				SubtitleSendJob.scheduleFor(
				        DateTime.now().plusMillis((int) sendTimeMsFromCurrentPosition), port, text,
				        spotNumber, textAppearanceFromSendingTime, textDurationMillis, i + 1,
				        NB_SEND);
			}
			return true;
		}
		return false;
	}
}
