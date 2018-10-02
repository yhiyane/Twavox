/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.process;

import java.io.File;

import org.apache.commons.cli.CommandLine;

public class AutoReadProcess implements Process {

	private final ReadProcess readProcess = new ReadProcess();

	@Override
	public void execute(CommandLine cmd) {
		// Get the directory path
		String dir = cmd.getOptionValue(MainProcess.OPTION_DIR);
		if (dir == null) {
			throw new IllegalArgumentException("Missing option -" + MainProcess.OPTION_DIR);
		}

		autoRead(dir);
	}

	private void autoRead(String f) {
		File file = new File(f);
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File child : file.listFiles()) {
					autoRead(child.getPath());
				}
			}
			if (file.isFile() && !file.isHidden()) {
				String name = file.getName();
				if (name.endsWith(".license")) {
					readProcess.readLicense(file.getPath());
				}
				if (name.endsWith(".key")) {
					readProcess.readKey(file.getPath());
				}
			}
		}
	}

	@Override
	public void printHelp() {
		System.out.println("Auto Read");
		System.out.println("\tRequired :");
		System.out.println("\t\t-action auto-read");
		System.out.println("\t\t-dir root directory");
	}

}
