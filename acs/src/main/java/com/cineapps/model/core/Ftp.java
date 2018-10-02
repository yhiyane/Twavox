/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.core;

public class Ftp {

	private final String host;
	private final String path;
	private final String user;
	private final String password;

	public Ftp(String host, String path, String user, String password) {
		this.host = host;
		this.path = path;
		this.user = user;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public String getPath() {
		return path;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public boolean isNull() {
		return host == null || path == null || user == null || password == null;
	}

	@Override
	public String toString() {
		return "Ftp [host=" + host + ", path=" + path + ", user=" + user + ", password=" + password
		        + "]";
	}
}
