/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;

public class ErrorCommunicationLogger {

	public static Logger getLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz);
		logger.setLevel(Level.ERROR);
		RollingFileAppender rollingFileAppender = new RollingFileAppender();
		rollingFileAppender.setMaxBackupIndex(1);
		rollingFileAppender.setMaxFileSize("1MB");
		rollingFileAppender.setFile("/var/log/acs/error-comm.log");
		return logger;
	}
}
