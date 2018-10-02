/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import org.apache.log4j.Logger;

public class CommunicationProperty extends AbstractProperty {

	private static final Logger logger = Logger.getLogger(CommunicationProperty.class);
	private boolean valid = true;

	private final String broadcastIp;
	private final int managerPort;

	public CommunicationProperty(String broadcastIp, String managerPort) {

		// Broadcast ip
		if ((this.broadcastIp = broadcastIp) == null) {
			logger.error("Missing property : [broadcastIp] in the properties file");
			this.valid = false;
		}
		// Manager port
		if (managerPort == null) {
			logger.error("Missing property : [managerPort] in the properties file");
			this.managerPort = 0;
			this.valid = false;
		} else {
			this.managerPort = Integer.parseInt(managerPort);
		}
	}

	public String getBroadcastIp() {
		return broadcastIp;
	}

	public int getManagerPort() {
		return managerPort;
	}

	public boolean isValid() {
		return valid;
	}
}
