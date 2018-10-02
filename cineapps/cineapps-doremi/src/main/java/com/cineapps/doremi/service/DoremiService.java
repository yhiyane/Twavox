/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.doremi.service;

import org.apache.log4j.Logger;

import com.cineapps.mediablock.core.connection.ConnectionException;
import com.cineapps.mediablock.core.logging.ErrorCommunicationLogger;

public class DoremiService {

	private static final Logger logger = ErrorCommunicationLogger.getLogger(DoremiService.class);
	private DoremiConnection connection;
	private String addr = null;

	public DoremiService(String addr, int port) {
		this.addr = addr;
		try {
			connection = new DoremiConnection(addr, port, false,
			        DoremiConnection.TIMEOUT_DEFAULT_DOREMI_IN_MS);
		} catch (ConnectionException e) {
			logger.error(e);
		}
	}

	public void openConnexion() throws ConnectionException {
		connection.connect();
	}

	public void closeConnexion() {
		connection.closeConnection();
	}
}
