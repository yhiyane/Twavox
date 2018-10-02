/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import org.apache.log4j.Logger;

public class MediablockProperty extends AbstractProperty {

	private static final Logger logger = Logger.getLogger(MediablockProperty.class);
	private boolean valid = true;

	private final String mediablockIp;
	private final int mediablockPort;
	private final String mediablockLogin;
	private final String mediablockPwd;
	private final String mediablockType;

	public MediablockProperty(String mediablockIp, String mediablockPort, String mediablockLogin,
	        String mediablockPwd, String mediablockType) {

		// Mediablock ip
		if ((this.mediablockIp = mediablockIp) == null) {
			logger.error("Missing property : [mediablockIp] in the properties file");
			this.valid = false;
		}
		// Mediablock port
		if (mediablockPort == null) {
			logger.error("Missing property : [mediablockPort] in the properties file");
			this.mediablockPort = 0;
			this.valid = false;
		} else {
			this.mediablockPort = Integer.parseInt(mediablockPort);
		}
		// Mediablock login
		if ((this.mediablockLogin = mediablockLogin) == null) {
			logger.error("Missing property : [mediablockLogin] in the properties file");
			this.valid = false;
		}
		// Mediablock password
		if ((this.mediablockPwd = mediablockPwd) == null) {
			logger.error("Missing property : [mediablockPwd] in the properties file");
			this.valid = false;
		}
		// Mediablock type
		if ((this.mediablockType = mediablockType) == null) {
			logger.error("Missing property : [mediablockType] in the properties file");
			this.valid = false;
		}
	}

	public boolean isValid() {
		return valid;
	}

	public String getMediablockIp() {
		return mediablockIp;
	}

	public int getMediablockPort() {
		return mediablockPort;
	}

	public String getMediablockLogin() {
		return mediablockLogin;
	}

	public String getMediablockPwd() {
		return mediablockPwd;
	}

	public String getMediablockType() {
		return mediablockType;
	}

}
