/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import java.util.List;

import org.apache.log4j.Logger;

public class SubtitleProperty extends AbstractProperty {

	private static final Logger logger = Logger.getLogger(SubtitleProperty.class);
	private boolean valid = true;

	private final String subtitlesRootDirectoryUrl;
	private final int subtitleNbProcessing;
	private final int subtitleDelay;
	private final List<String> subtitleFreeLanguages;
	private final int subtitleDefaultPort;
	private final int webServicePort = 23122;

	public SubtitleProperty(String subtitlesRootDirectoryUrl, String subtitleNbProcessing,
	        String subtitleDelay, String subtitleFreeLanguages, String subtitleDefaultPort) {

		// Subtitle root directory url
		if ((this.subtitlesRootDirectoryUrl = subtitlesRootDirectoryUrl) == null) {
			logger.error("Missing property : [subtitlesRootDirectoryUrl] in the properties file");
			this.valid = false;
		}
		// Subtitle nb processing
		if (subtitleNbProcessing == null) {
			logger.error("Missing property : [subtitleNbProcessing] in the properties file");
			this.subtitleNbProcessing = 0;
			this.valid = false;
		} else {
			this.subtitleNbProcessing = Integer.parseInt(subtitleNbProcessing);
		}
		// Subtitle delay
		if (subtitleDelay == null) {
			logger.error("Missing property : [subtitleDelay] in the properties file");
			this.subtitleDelay = 0;
			this.valid = false;
		} else {
			this.subtitleDelay = Integer.parseInt(subtitleDelay);
		}
		// Subtitle free languages
		if ((this.subtitleFreeLanguages = getListFromValues(subtitleFreeLanguages)) == null) {
			logger.error("Missing property : [subtitleFreeLanguages] in the properties file");
			this.valid = false;
		}
		// Subtitle default port
		if (subtitleDefaultPort == null) {
			logger.error("Missing property : [subtitleDefaultPort] in the properties file");
			this.subtitleDefaultPort = 0;
			this.valid = false;
		} else {
			this.subtitleDefaultPort = Integer.parseInt(subtitleDefaultPort);
		}
	}

	public boolean isValid() {
		return valid;
	}

	public String getSubtitlesRootDirectoryUrl() {
		return subtitlesRootDirectoryUrl;
	}

	public int getSubtitleNbProcessing() {
		return subtitleNbProcessing;
	}

	public int getSubtitleDelay() {
		return subtitleDelay;
	}

	public List<String> getSubtitleFreeLanguages() {
		return subtitleFreeLanguages;
	}

	public int getSubtitleDefaultPort() {
		return subtitleDefaultPort;
	}

	public int getWebServicePort() {
		return webServicePort;
	}
}
