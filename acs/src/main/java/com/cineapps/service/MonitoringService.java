/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cineapps.model.monitoring.StateDescription;
import com.cineapps.model.monitoring.Status;
import com.cineapps.model.property.PropertyLoader;
import com.cineapps.model.subtitle.SubtitleInfo;
import com.cineapps.util.FileSystemUtils;

public class MonitoringService implements IService {

	private static final Logger logger = Logger.getLogger(MonitoringService.class);
	private static final String JOB_GROUP = "MonitoringJobGroup";

	// Connection
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private DataOutputStream outToClient;

	// Status
	private Status status;

	public MonitoringService() {
	}

	@Override
	public void startProcess() {
		final PropertyLoader propertyLoader = ServicesHolder.getInstance().getPropertyLoader();

		initStatus(propertyLoader);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(propertyLoader.getCommunicationProperty()
					        .getManagerPort());
					while (true) {
						connectionSocket = serverSocket.accept();

						// Streams
						final BufferedReader inFromClient = new BufferedReader(
						        new InputStreamReader(connectionSocket.getInputStream(), "UTF-8"));
						outToClient = new DataOutputStream(connectionSocket.getOutputStream());

						// Welcome message
						monitor();

						new Thread(new Runnable() {

							public void run() {
								try {
									String fromServer;
									while ((fromServer = inFromClient.readLine()) != null) {
										execute(fromServer);
									}
								} catch (IOException e) {
									logger.error(e);
								}
							}
						}).start();
					}
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}).start();

	}

	@Override
	public void stopProcess() {
		try {
			if (connectionSocket != null) {
				connectionSocket.close();
				connectionSocket = null;
			}
			if (serverSocket != null) {
				serverSocket.close();
				serverSocket = null;
			}
			if (outToClient != null) {
				outToClient.close();
				outToClient = null;
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}

	@Override
	public String getJobGroup() {
		return JOB_GROUP;
	}

	//@formatter:off
	/**
	 * Initializes the monitor status instance with default values.
	 * 
	 * @param propertyLoader
	 */
	//@formatter:on
	private void initStatus(PropertyLoader propertyLoader) {
		status = new Status();
		StateDescription stateDescription = StateDescription.STOP;
		status.setState(stateDescription.getState());
		status.setMessage(stateDescription.getDescription());
		String subtitlesRootDirectoryUrl = propertyLoader.getSubtitleProperty()
		        .getSubtitlesRootDirectoryUrl();
		status.setFolders(FileSystemUtils.getList(subtitlesRootDirectoryUrl));
		status.setDiskPercent(Integer.toString(100 - FileSystemUtils
		        .getAvailablePercent(subtitlesRootDirectoryUrl)));
	}

	//@formatter:off
	/**
	 * Monitor a "Stop" message.
	 */
	//@formatter:on
	public void monitorStop() {
		StateDescription stateDescription = StateDescription.STOP;
		status.setState(stateDescription.getState());
		status.setDcp(null);
		status.setMessage(stateDescription.getDescription());
		status.setSubtitles(null);
		status.setDetail(null);
		monitor();
	}

	//@formatter:off
	/**
	 * Monitor a "Scan mediablock" message.
	 */
	//@formatter:on
	public void monitorScanMediablock() {
		StateDescription stateDescription = StateDescription.SCAN_MEDIABLOCK;
		status.setState(stateDescription.getState());
		status.setDcp(null);
		status.setMessage(stateDescription.getDescription());
		status.setSubtitles(null);
		status.setDetail(null);
		monitor();
	}

	//@formatter:off
	/**
	 * Monitor a "Scan library" message.
	 */
	//@formatter:on
	public void monitorScanLibrary(String dcpName) {
		StateDescription stateDescription = StateDescription.SCAN_LIBRARY;
		status.setMessage(stateDescription.getDescription());
		status.setState(stateDescription.getState());
		status.setDcp(dcpName);
		monitor();
	}

	//@formatter:off
	/**
	 * Monitor a "Download" message.
	 */
	//@formatter:on
	public void monitorDownload() {
		StateDescription stateDescription = StateDescription.DOWNLOAD;
		status.setMessage(stateDescription.getDescription());
		status.setState(stateDescription.getState());
		monitor();
	}

	//@formatter:off
	/**
	 * Update the list of subtitle files and the available disk space.
	 */
	//@formatter:on
	public void updateStatus() {
		PropertyLoader propertyLoader = ServicesHolder.getInstance().getPropertyLoader();
		String subtitlesRootDirectoryUrl = propertyLoader.getSubtitleProperty()
		        .getSubtitlesRootDirectoryUrl();
		status.setFolders(FileSystemUtils.getList(subtitlesRootDirectoryUrl));
		status.setDiskPercent(Integer.toString(100 - FileSystemUtils
		        .getAvailablePercent(subtitlesRootDirectoryUrl)));
	}

	//@formatter:off
	/**
	 * Monitor a "Play" message with the list of processed subtitles
	 * informations.
	 */
	//@formatter:on
	public void monitorPlay(String plName, List<SubtitleInfo> processingSubtitles) {
		StateDescription stateDescription = StateDescription.PLAY;
		status.setState(stateDescription.getState());
		status.setMessage(String.format(stateDescription.getDescription(),
		        processingSubtitles.size()));
		status.setDcp(plName);
		Map<String, String> subtitles = new HashMap<>();
		for (SubtitleInfo subtitleInfo : processingSubtitles) {
			subtitles.put(subtitleInfo.getLang(), subtitleInfo.getDcpTitle());
		}
		status.setSubtitles(subtitles);
		monitor();
	}

	//@formatter:off
	/**
	 * Monitor a "Play" message with an empty subtitle list.
	 */
	//@formatter:on
	public void monitorPlayEmpty(String plName) {
		StateDescription stateDescription = StateDescription.PLAY_EMPTY;
		status.setState(stateDescription.getState());
		status.setMessage(stateDescription.getDescription());
		status.setDcp(plName);
		status.setSubtitles(new HashMap<String, String>());
		monitor();
	}

	//@formatter:off
	/**
	 * Monitor an error message from a custom detailed message.
	 */
	//@formatter:on
	public void monitorError(String header, String detail) {
		StateDescription stateDescription = StateDescription.ERROR;
		status.setState(stateDescription.getState());
		status.setMessage(String.format(stateDescription.getDescription(), header));
		status.setDcp(null);
		status.setDetail(detail);
		status.setSubtitles(null);
		monitor();
	}

	//@formatter:off
	/**
	 * Monitor an error message from an exception.
	 */
	//@formatter:on
	public void monitorException(String header, Exception e) {
		// Transform exception to readable string
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String detail = sw.toString().replace("\n", "<br />");
		// update the status
		StateDescription stateDescription = StateDescription.ERROR;
		status.setState(stateDescription.getState());
		status.setMessage(String.format(stateDescription.getDescription(), header));
		status.setDcp(null);
		status.setDetail(detail);
		status.setSubtitles(null);
		// monitor
		monitor();
	}

	//@formatter:off
	/**
	 * Expect in parameter a boolean to know if the wifi is reachable.
	 * If this value is different of the old value, monitor it. 
	 */
	//@formatter:on
	public void monitorWifiReachable(boolean newWifiReachable) {
		if (status.isWifiReachable() != newWifiReachable) {
			status.setWifiReachable(newWifiReachable);
			monitor();
		}
	}

	//@formatter:off
	/**
	 * Sends the current state to the Manager application.
	 */
	//@formatter:on
	private void monitor() {
		// Before to send a notification to the monitoring screen,
		// updates the indicators values.
		status.updateIndicators();

		if (outToClient != null) {
			try {
				String s = status.toString() + "\n";
				outToClient.writeBytes(s);
			} catch (IOException e) {
				logger.error(e);
				try {
					outToClient.close();
				} catch (IOException e1) {
					logger.error(e1);
				}
				outToClient = null;
			}
		}
	}

	//@formatter:off
	/**
	 * Executes the command if it is recognized.
	 * The command comes from the Manager application.
	 * The possible values are :
	 * - "RESTART"
	 * - "REBOOT"
	 * - "DELETEmovieName"
	 * 
	 * @param command
	 */
	//@formatter:on
	private void execute(String command) {

		ServicesHolder servicesHolder = ServicesHolder.getInstance();

		if ("RESTART".equals(command)) {
			logger.info("Restart");
			servicesHolder.restart();
		} else if ("REBOOT".equals(command)) {
			logger.info("Reboot");
			servicesHolder.reboot();
		} else if (command.startsWith("DELETE")) {
			String folderName = command.substring("DELETE".length());
			String rootDirectory = servicesHolder.getPropertyLoader().getSubtitleProperty()
			        .getSubtitlesRootDirectoryUrl();

			String path = rootDirectory + (rootDirectory.endsWith("/") ? "" : "/") + folderName;
			if (FileSystemUtils.deleteDirectory(path)) {
				updateStatus();
				monitor();
			} else {
				monitorError("[Deletion]", "A problem occurs when deleting the folder at " + path);
			}
		} else {
			logger.error("Unknown command from the monitoring : " + command);
		}
	}
}
