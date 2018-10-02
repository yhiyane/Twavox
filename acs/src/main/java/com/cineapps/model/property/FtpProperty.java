/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cineapps.model.core.Ftp;

public class FtpProperty extends AbstractProperty {

	private static final Logger logger = Logger.getLogger(FtpProperty.class);
	private final List<Ftp> ftps;

	public FtpProperty() {
		ftps = new ArrayList<>();
	}

	public boolean isValid() {
		return true;
	}

	public List<Ftp> getFtps() {
		return ftps;
	}

	public void addFtp(Ftp ftp) {
		if (ftp.isNull()) {
			logger.error("Cannot add a null FTP");
		} else {
			ftps.add(ftp);
		}
	}
}
