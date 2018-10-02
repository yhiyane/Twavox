/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.main;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.cineapps.model.property.LicenseProperty;
import com.cineapps.model.property.PropertyLoader;
import com.cineapps.service.LicenseService;
import com.cineapps.service.ServicesHolder;

/**
 * Main class executed by the jar file.
 */
public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		// Log the version of the ACS
		// The version file will be updated during the jar packaging
		String version = IOUtils.toString(
		        Main.class.getClass().getResourceAsStream("/version.txt"), "UTF-8");
		logger.info("##### ACS " + version + " #####");

		if (args.length != 1) {
			throw new IllegalArgumentException("Missing path to acs.properties file");
		}

		PropertyLoader propertyLoader = new PropertyLoader(args[0]);
		if (propertyLoader.isValid()) {
			// Initializes all the services with the properties file
			ServicesHolder servicesHolder = ServicesHolder.getInstance();
			servicesHolder.init(propertyLoader);

			LicenseProperty licenseProperty = propertyLoader.getLicenseProperty();
			LicenseService licenseService = servicesHolder.getLicenseService();
			// Generate a key if no exists
			licenseService.generateKeyIfNeeded(licenseProperty.getKeyFileUrl());
			// Load the license
			licenseService.loadLicense(licenseProperty.getLicenseFileUrl());
			// Check the license
			if (licenseService.isLicenseValid()) {
				servicesHolder.start();
			} else {
				logger.error("License invalid");
			}

		} else {
			logger.error("The properties file is not valid");
		}
	}
}
