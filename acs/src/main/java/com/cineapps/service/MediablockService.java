/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;

import com.cineapps.job.sync.MediablockSynchronizerJob;
import com.cineapps.model.mediablock.MediablockSpl;
import com.cineapps.model.mediablock.MediablockState;
import com.cineapps.model.mediablock.MediablockType;
import com.cineapps.util.SchedulerUtils;
import com.cineapps.util.TimeUtils;
import com.dvidea.core.mediablock.plugin.IPlugin;
import com.dvidea.core.mediablock.plugin.MainElement;
import com.dvidea.core.mediablock.plugin.PluginStatus;
import com.dvidea.core.mediablock.plugin.SPLElementInfos;
import com.dvidea.core.mediablock.plugin.SPLInfos;

public class MediablockService implements IService {

	private static final Logger logger = Logger.getLogger(MediablockService.class);
	private IPlugin plugin;
	private MediablockState mediablockState = new MediablockState();
	private boolean tryingConnection = false;
	private int lastMonitoredId = -1;
	private long lastMonitoredTime = 0;

	private static final String JOB_GROUP = "MediablockJobGroup";
	private boolean enableLog = true;
	private final int MONITORING_DELAY_MILLIS = 1 * 60 * 60 * 1000;
	private final List<String> ACCEPTED_CONTENTS = Arrays.asList("_FTR");

	public MediablockService() {

	}

	// For test scope
	public void setPlugin(IPlugin plugin) {
		this.plugin = plugin;
	}

	// For test scope
	public IPlugin getPlugin() {
		return plugin;
	}

	//@formatter:off
	/**
	 * Connects to the mediablock with parameters. 
	 * Protected visibility for test purpose. 
	 * DO NOT USE THIS METHOD OUT OF TEST ENVIRONNEMENT, USE connect() INSTEAD.
	 * 
	 * @param ip
	 * @param port
	 * @param login
	 * @param password
	 * @param type
	 */
	//@formatter:on
	protected void initPlugin(String ip, int port, String login, String password, String type) {
		MediablockType mediablockType = MediablockType.fromName(type);
		if (mediablockType == null) {
			logger.error("Mediablock type class not found " + type);
			return;
		}
		try {
			plugin = (IPlugin) Class.forName(mediablockType.getClazz()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			logger.error("Error when generating the plugin with type " + mediablockType);
			logger.error(e);
		}

		if (plugin != null) {
			plugin.init(ip, port, login, password, new String[0]);
		}
	}

	//@formatter:off
	/**
	 * Disconnects to the server plugin.
	 */
	//@formatter:on
	public void disposePlugin() {
		if (plugin != null) {
			plugin.dispose();
			plugin = null;
		}
	}

	//@formatter:off
	/**
	 * Returns true if the connection to the server plugin succeed, false
	 * otherwise.
	 */
	//@formatter:on
	public boolean connect(String host, int port, String login, String password, String type) {
		if (plugin == null && !tryingConnection) {
			tryingConnection = true;
			initPlugin(host, port, login, password, type);
			tryingConnection = false;
		}
		return tryingConnection ? false : plugin != null;
	}

	//@formatter:off
	/**
	 * Returns the status of the server.
	 * 
	 */
	//@formatter:on
	public PluginStatus getPluginStatus() {
		if (plugin != null) {
			PluginStatus pluginStatus = plugin.getStatus();
			if (pluginStatus.getError() == PluginStatus.NO_ERROR) {
				return pluginStatus;
			}
		}
		disposePlugin();
		return null;
	}

	//@formatter:off
	/**
	 * @return the state of the mediablock. Never be null.
	 */
	//@formatter:on
	public MediablockState getMediablockState() {
		return mediablockState;
	}

	//@formatter:off
	/**
	 * Returns true if the dcp is accepted (FTR), false
	 * otherwise.
	 * 
	 * @param dcpName
	 * @return
	 */
	//@formatter:on
	private boolean acceptContent(String dcpName) {
		for (String acceptedContent : ACCEPTED_CONTENTS) {
			if (dcpName.contains(acceptedContent)) {
				return true;
			}
		}
		return false;
	}

	//@formatter:off
	/**
	 * Calculates the pre show duration in milliseconds and gets the name of the feature.
	 * Returns null if an error occurs.
	 * 
	 * @return
	 */
	//@formatter:off
	public MediablockSpl getCurrentPlaylist() {
		logger.info("Retrieve the name of the movie in the current playlist...");
		PluginStatus pluginStatus = getPluginStatus();
		Map<Integer, Integer> editRates = new HashMap<Integer, Integer>();

		// Find the current spl
		UUID splUuid = pluginStatus.getPlId();
		SPLInfos spl = plugin.getSPL(splUuid);

		// Calculate the preshow duration
		if (spl != null) {
			List<SPLElementInfos> elements = spl.getContentList();
			logger.info("Nb elements in the playlist : " + elements.size());
			int selectedDuration = 0;
			int selectedNumerator = 0;
			String selectedName = null;
			
			for (SPLElementInfos element : elements) {
				MainElement el = element.getContent();
				String name = el.getName();
				// Get the last accepted content 
				if (acceptContent(name)) {
					selectedName = name;
					selectedDuration = el.getEditableUnitDuration();
					selectedNumerator = el.getEditRate().getNumerator();
				}
				// Calculate the ms of the pre show element
				int editDuration = el.getEditableUnitDuration();
				int numerator = el.getEditRate().getNumerator();
				// Put all editRateDuration sorted by the numerator of the
				// edit rate
				Integer editDurationInMap = editRates.get(numerator);
				if (editDurationInMap == null) {
					editDurationInMap = editDuration;
				} else {
					editDurationInMap += editDuration;
				}
				editRates.put(numerator, editDurationInMap);
			}
			
			// If found an accepted content
			if (selectedName != null) {
				// Substract the duration of the selected element
				int currentEditDuration = editRates.get(selectedNumerator);
				currentEditDuration -= selectedDuration;
				editRates.put(selectedNumerator, currentEditDuration);
				
				// Returns the informations
				int preshowDuration_ms = 0;
				for (Integer editRateNumerator : editRates.keySet()) {
					preshowDuration_ms += TimeUtils.editRateToMillis(
					        editRates.get(editRateNumerator), editRateNumerator);
				}
				return new MediablockSpl(selectedName, preshowDuration_ms);
			}
			
			
		} else {
			logger.error("No SPL with uuid " + splUuid);
			logger.error("Are you playing a playlist ?");
		}
		return null;
	}

	@Override
	public void startProcess() {
		MediablockSynchronizerJob.scheduleNow(getJobGroup());
	}

	@Override
	public void stopProcess() {
		// Removes all schedules subtitle jobs
		Scheduler scheduler = SchedulerUtils.getScheduler();
		List<JobKey> mediablockJobKeys;
		try {
			mediablockJobKeys = new ArrayList<>(scheduler.getJobKeys(GroupMatcher
			        .jobGroupEquals(getJobGroup())));
			scheduler.deleteJobs(mediablockJobKeys);
			logger.info("Stops all mediablock jobs : " + mediablockJobKeys.size()
			        + " scheduled jobs");
		} catch (SchedulerException e) {
			logger.error(e);
		}

		// Dispose the connection
		disposePlugin();
	}

	@Override
	public String getJobGroup() {
		return JOB_GROUP;
	}

	//@formatter:off
	/**
	 * Returns true if the message with id can be monitored, false otherwise. 
	 * The same message is monitored after waiting a duration.
	 * This method is used to not over-push the notifications.
	 * 
	 * @param id
	 * @return
	 */
	//@formatter:on
	public boolean canMonitor(int id) {
		if (enableLog
		        || (id == lastMonitoredId && (System.currentTimeMillis() - lastMonitoredTime) > MONITORING_DELAY_MILLIS)) {
			enableLog = false;
			lastMonitoredId = id;
			lastMonitoredTime = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	//@formatter:off
	/**
	 * Enables the log for this class.
	 */
	//@formatter:on
	public void enableLog() {
		enableLog = true;
	}
}
