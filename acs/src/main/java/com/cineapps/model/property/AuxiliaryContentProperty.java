/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import org.apache.log4j.Logger;

public class AuxiliaryContentProperty extends AbstractProperty {

	private static final Logger logger = Logger.getLogger(AuxiliaryContentProperty.class);
	private boolean valid = true;

	private final String posterUrl;
	private final String synopsisUrl;
	private final String auxiliaryContentDirUrl;

	public AuxiliaryContentProperty(String posterUrl, String synopsisUrl,
	        String auxiliaryContentDirUrl) {

		// Poster url
		if ((this.posterUrl = posterUrl) == null) {
			logger.error("Missing property : [posterUrl] in the properties file");
			this.valid = false;
		}
		// Synopsis url
		if ((this.synopsisUrl = synopsisUrl) == null) {
			logger.error("Missing property : [synopsisUrl] in the properties file");
			this.valid = false;
		}
		// Auxiliary content directory url
		if ((this.auxiliaryContentDirUrl = auxiliaryContentDirUrl) == null) {
			logger.error("Missing property : [auxiliaryContentDirUrl] in the properties file");
			this.valid = false;
		}
	}

	public boolean isValid() {
		return valid;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public String getSynopsisUrl() {
		return synopsisUrl;
	}

	public String getAuxiliaryContentDirUrl() {
		return auxiliaryContentDirUrl;
	}
}
