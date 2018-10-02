/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.cineapps.model.core.Ftp;

public class PropertyLoader {

	private static final Logger logger = Logger.getLogger(PropertyLoader.class);
	private boolean valid = true;

	private LicenseProperty licenseProperty;
	private CommunicationProperty communicationProperty;
	private MediablockProperty mediablockProperty;
	private FtpProperty ftpProperty;
	private SubtitleProperty subtitleProperty;
	private AuxiliaryContentProperty auxiliaryContentProperty;

	public PropertyLoader(String propertyFilePath) {
		logger.info("Loading the properties file " + propertyFilePath);
		load(propertyFilePath);
	}

	/**
	 * Initializes the variables with the property file.
	 * 
	 * @param prop
	 */
	private void load(String propertyFilePath) {
		// Load the .properties file
		Properties prop = new Properties();
		InputStream input = null;
		if (propertyFilePath != null) {
			try {
				input = new FileInputStream(propertyFilePath);
				prop.load(input);
			} catch (Exception e) {
				logger.error("Error when loading the .properties file");
				logger.error(e);
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						logger.error("Cannot close input stream");
						logger.error(e);
					}
				}
			}
		}
		// Load all properties
		loadAllProperties(prop);
	}

	/**
	 * Verify that all needed informations are present in the property files. If
	 * an attribute is missing, this method returns false.
	 */
	private void loadAllProperties(Properties prop) {

		// Load the properties for the license
		licenseProperty = new LicenseProperty(prop.getProperty("license.key.file.url"),
		        prop.getProperty("license.file.url"));

		// Load the properties for the communication
		communicationProperty = new CommunicationProperty(prop.getProperty("broadcast.ip"),
		        prop.getProperty("manager.port"));

		// Load the properties for the mediablock
		mediablockProperty = new MediablockProperty(prop.getProperty("mediablock.ip"),
		        prop.getProperty("mediablock.port"), prop.getProperty("mediablock.login"),
		        prop.getProperty("mediablock.password"), prop.getProperty("mediablock.type"));

		// Load the properties for the ftp servers
		ftpProperty = new FtpProperty();
		// Add the mediablock as FTP server
		ftpProperty.addFtp(new Ftp(prop.getProperty("ftp.mediablock.ip"), prop
		        .getProperty("ftp.mediablock.path"), prop.getProperty("ftp.mediablock.user"), prop
		        .getProperty("ftp.mediablock.password")));
		// Add the library
		ftpProperty.addFtp(new Ftp(prop.getProperty("ftp.library.ip"), prop
		        .getProperty("ftp.library.path"), prop.getProperty("ftp.library.user"), prop
		        .getProperty("ftp.library.password")));

		// Load the properties for the subtitle process
		subtitleProperty = new SubtitleProperty(prop.getProperty("subtitle.root.directory"),
		        prop.getProperty("subtitle.nb.processing"), prop.getProperty("subtitle.delay.ms"),
		        prop.getProperty("subtitle.free.languages"),
		        prop.getProperty("subtitle.default.port"));

		// Load the properties for the auxiliary content process
		auxiliaryContentProperty = new AuxiliaryContentProperty(prop.getProperty("poster.url"),
		        prop.getProperty("synopsis.url"),
		        prop.getProperty("auxiliary.content.directory.url"));

		// Calculate the global validity
		valid = true && licenseProperty.isValid() && communicationProperty.isValid()
		        && mediablockProperty.isValid() && ftpProperty.isValid()
		        && subtitleProperty.isValid() && auxiliaryContentProperty.isValid();
	}

	public boolean isValid() {
		return valid;
	}

	public LicenseProperty getLicenseProperty() {
		return licenseProperty;
	}

	public CommunicationProperty getCommunicationProperty() {
		return communicationProperty;
	}

	public MediablockProperty getMediablockProperty() {
		return mediablockProperty;
	}

	public FtpProperty getFtpProperty() {
		return ftpProperty;
	}

	public SubtitleProperty getSubtitleProperty() {
		return subtitleProperty;
	}

	public AuxiliaryContentProperty getAuxiliaryContentProperty() {
		return auxiliaryContentProperty;
	}
}
