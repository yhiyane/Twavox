/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.acs_ftp.model;

public class Ftp {

	private final String host;
	private final String user;
	private final String password;
	private final String path;

	public Ftp(String host, String user, String password, String path) {
		this.host = host;
		this.user = user;
		this.password = password;
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getPath() {
		return path;
	}

}
