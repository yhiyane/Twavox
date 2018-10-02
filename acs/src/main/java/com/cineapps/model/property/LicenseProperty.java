/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import org.apache.log4j.Logger;

public class LicenseProperty extends AbstractProperty {

	private static final Logger logger = Logger.getLogger(LicenseProperty.class);
	private boolean valid = true;

	private final String keyFileUrl;
	private final String licenseFileUrl;

	public LicenseProperty(String keyFileUrl, String licenseFileUrl) {

		// License key file
		if ((this.keyFileUrl = keyFileUrl) == null) {
			logger.error("Missing property : [keyFileUrl] in the properties file");
			this.valid = false;
		}
		// License file
		if ((this.licenseFileUrl = licenseFileUrl) == null) {
			logger.error("Missing property : [licenseFileUrl] in the properties file");
			this.valid = false;
		}
	}

	public boolean isValid() {
		return valid;
	}

	public String getKeyFileUrl() {
		return keyFileUrl;
	}

	public String getLicenseFileUrl() {
		return licenseFileUrl;
	}
}
