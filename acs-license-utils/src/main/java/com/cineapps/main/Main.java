/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.main;

import org.apache.commons.cli.ParseException;

import com.cineapps.process.MainProcess;

public class Main {

	public static void main(String[] args) throws ParseException {
		new MainProcess().execute(args);
	}

}
