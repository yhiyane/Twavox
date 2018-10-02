/**
 * Copyright (c) 2014 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.sample;

import com.cineapps.connector.GdcConnector;

public class Main {

	public static void main(String[] args) {
		String ip = "localhost";
		int port = 11730;
		String login = "manager";
		String password = "password";

		GdcConnector connector = new GdcConnector();
		// SonyConnector connector = new SonyConnector();
		// DoremiConnector connector = new DoremiConnector();
		try {
			connector.init(ip, port, login, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(connector.getStatus());
	}
}
