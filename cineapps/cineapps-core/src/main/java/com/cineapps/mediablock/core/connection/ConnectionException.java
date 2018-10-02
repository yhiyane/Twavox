/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.connection;

public class ConnectionException extends Exception {

	private static final long serialVersionUID = -7654278364372350852L;

	public ConnectionException() {
		super();
	}

	public ConnectionException(String arg0) {
		super(arg0);
	}

	public ConnectionException(Throwable arg0) {
		super(arg0);
	}

	public ConnectionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
