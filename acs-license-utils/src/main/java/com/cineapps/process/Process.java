/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.process;

import org.apache.commons.cli.CommandLine;

public interface Process {

	public void execute(CommandLine cmd);

	public void printHelp();
}
