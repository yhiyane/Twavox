/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.cineapps.model.property.CommunicationProperty;
import com.cineapps.model.property.PropertyLoader;

//@formatter:off
/**
 * Holds all the services. 
 * We use the singleton pattern instead of Spring IOC to
 * keep the states (that would be used for Spring library).
 * It's a deliberate choice to not use Spring due to limited memory
 * in Twavox. 
 */
//@formatter:on
public class ServicesHolder {

	private static final Logger logger = Logger.getLogger(ServicesHolder.class);
	private static final ServicesHolder instance = new ServicesHolder();

	private PropertyLoader propertyLoader;

	private LicenseService licenseService;
	private SubtitleService subtitleService;
	private MediablockService mediablockService;
	private BroadcastService broadcastService;
	private MultiStService multiStService;
	private FtpService ftpService;
	private MonitoringService monitoringService;
	private WifiListenerService wifiListenerService;

	private ServicesHolder() {
		// private constructor
	}

	public static ServicesHolder getInstance() {
		return instance;
	}

	/**
	 * Initializes the services
	 */
	public void init(PropertyLoader propertyLoader) {
		this.propertyLoader = propertyLoader;
		CommunicationProperty communicationProperty = propertyLoader.getCommunicationProperty();
		this.licenseService = new LicenseService();
		this.subtitleService = new SubtitleService();
		this.mediablockService = new MediablockService();
		this.broadcastService = new BroadcastService(communicationProperty.getBroadcastIp());
		this.multiStService = new MultiStService();
		this.ftpService = new FtpService();
		this.monitoringService = new MonitoringService();
		this.wifiListenerService = new WifiListenerService();
	}

	/**
	 * Starts the services.
	 */
	public void start() {
		this.licenseService.startProcess();
		this.monitoringService.startProcess();
		this.subtitleService.startProcess();
		this.broadcastService.startProcess();
		this.ftpService.startProcess();
		this.multiStService.startProcess();
		this.wifiListenerService.startProcess();

		// The mediablockService has to be at the end because the others
		// services have to be instantiated before it runs
		this.mediablockService.startProcess();
	}

	/**
	 * Stops the services.
	 */
	public void stop() {
		licenseService.stopProcess();
		subtitleService.stopProcess(true);
		mediablockService.stopProcess();
		broadcastService.stopProcess();
		multiStService.stopProcess();
		ftpService.stopProcess();
		monitoringService.stopProcess();
		this.wifiListenerService.stopProcess();
	}

	/**
	 * Restarts the application.
	 */
	public void restart() {
		// Stop properly
		stop();

		// Restart
		try {
			Runtime.getRuntime().exec("/etc/init.d/acs restart");
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * Reboots the Twavox and the Wifi router.
	 */
	public void reboot() {
		// Stop properly
		stop();

		// Reboot
		try {
			Runtime.getRuntime().exec("/etc/init.d/acs reboot");
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * GETTERS
	 */

	public PropertyLoader getPropertyLoader() {
		return propertyLoader;
	}

	public LicenseService getLicenseService() {
		return licenseService;
	}

	public SubtitleService getSubtitleService() {
		return subtitleService;
	}

	public MediablockService getMediablockService() {
		return mediablockService;
	}

	public BroadcastService getBroadcastService() {
		return broadcastService;
	}

	public MultiStService getMultiStService() {
		return multiStService;
	}

	public FtpService getFtpService() {
		return ftpService;
	}

	public MonitoringService getMonitoringService() {
		return monitoringService;
	}

	public WifiListenerService getWifiListenerService() {
		return wifiListenerService;
	}
}
