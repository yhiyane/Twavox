/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;

import com.cineapps.job.subtitle.SubtitleSendJob;
import com.cineapps.model.property.AuxiliaryContentProperty;
import com.cineapps.model.property.PropertyLoader;
import com.cineapps.model.subtitle.SubtitleInfo;
import com.cineapps.util.AuxiliaryContentUtils;
import com.cineapps.util.SchedulerUtils;

public class SubtitleService implements IService {

	private static final String JOB_GROUP = "SubtitleJobGroup";

	private static final Logger logger = Logger.getLogger(SubtitleService.class);
	private boolean processing = false;
	private String dcpName;
	private int nbProcess = 0;
	private List<SubtitleInfo> processingSubtitles = new ArrayList<>();
	private Transformer transformer;

	public SubtitleService() {
		newTransformer();
	}

	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;
	}

	public String getDcpName() {
		return dcpName;
	}

	public void setDcpName(String dcpName) {
		this.dcpName = dcpName;
	}

	public List<SubtitleInfo> getProcessingSubtitles() {
		return processingSubtitles;
	}

	public Transformer getTransformer() {
		if (transformer == null) {
			newTransformer();
		}
		return transformer;
	}

	private void newTransformer() {
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			logger.error(e);
			transformer = null;
		}
	}

	public void setTransformer(Transformer transformer) {
		this.transformer = transformer;
	}

	// @formatter:off
	/**
	 * Warns that a process for a subtitle will fire.
	 */
	// @formatter:on
	public void warnProcessFire(SubtitleInfo subtitleInfo) {
		nbProcess++;
		if (!processing) {
			processing = true;
		}
		processingSubtitles.add(subtitleInfo);
	}

	// @formatter:off
	/**
	 * Warns that a process for a subtitle will end. 
	 * If there are no more running processes, clears the service.
	 */
	// @formatter:on
	public void warnProcessEnd(boolean monitor) {
		nbProcess--;
		if (nbProcess <= 0) {
			logger.info("The subtitle service stops the process");
			nbProcess = 0;
			onEndProcess(monitor);
		}
	}

	// @formatter:off
	/**
	 * The method is called when the service will stop.
	 * Do the last operations.
	 */
	// @formatter:on
	private void onEndProcess(boolean monitor) {
		// Get the properties
		ServicesHolder servicesHolder = ServicesHolder.getInstance();
		PropertyLoader propertyLoader = servicesHolder.getPropertyLoader();
		AuxiliaryContentProperty auxiliaryContentProperty = propertyLoader
		        .getAuxiliaryContentProperty();
		MonitoringService monitoringService = servicesHolder.getMonitoringService();
		BroadcastService broadcastService = servicesHolder.getBroadcastService();

		// Send stop command to all connected devices
		// On this notification receiving, the devices will stop to display
		// the text and clear the buffer
		for (SubtitleInfo subtitleInfo : processingSubtitles) {
			SubtitleSendJob.sendStop(broadcastService, subtitleInfo.getPort());
		}
		// Hide auxiliary content
		AuxiliaryContentUtils.hideAuxiliaryContent(auxiliaryContentProperty.getPosterUrl(),
		        auxiliaryContentProperty.getSynopsisUrl());
		// Clear the variables
		processing = false;
		dcpName = null;
		processingSubtitles.clear();
		// Monitoring
		if (monitor) {
			monitoringService.monitorStop();
		}
	}

	@Override
	public void startProcess() {
		// DO NOTHING
		// Cannot start the subtitle service without the title of the
		// playing dcp
		// See the MediablockSynchronizerJob class
	}

	/**
	 * @deprecated use {@link #stopProcess(boolean monitor)} instead.
	 */
	@Override
	@Deprecated
	public void stopProcess() {

	}

	// @formatter:off
	/**
	 * Stops the service, deletes all scheduled jobs and reinitializes the values.
	 * 
	 * @param monitor
	 *            true if we want to monitor the Stop state, false otherwise.
	 */
	// @formatter:on
	public void stopProcess(boolean monitor) {
		try {
			// Removes all scheduled subtitle jobs
			Scheduler scheduler = SchedulerUtils.getScheduler();
			List<JobKey> subtitleJobKeys = new ArrayList<>(scheduler.getJobKeys(GroupMatcher
			        .jobGroupEquals(getJobGroup())));
			scheduler.deleteJobs(subtitleJobKeys);
			logger.info("Stops all subtitle jobs : " + subtitleJobKeys.size() + " scheduled jobs");

			// Release the process lock
			nbProcess = 0;
			warnProcessEnd(monitor);

		} catch (SchedulerException e) {
			logger.error(e);
		}
	}

	@Override
	public String getJobGroup() {
		return JOB_GROUP;
	}
}
