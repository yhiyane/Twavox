/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.process;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli.CommandLine;

public class AutoGenProcess implements Process {

	private final String DATE_FORMAT = "yyyy-MM-dd";
	private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	private final int DEFAULT_MULTI_ST_NB_COINS = -1;
	private final boolean DEFAULT_AUX_CONTENT = false;
	private final GenerateProcess generateProcess = new GenerateProcess();

	@Override
	public void execute(CommandLine cmd) {
		// Get the directory path
		String dir = cmd.getOptionValue(MainProcess.OPTION_DIR);
		if (dir == null) {
			throw new IllegalArgumentException("Missing option -" + MainProcess.OPTION_DIR);
		}

		// Get the validity value
		String validityValue = cmd.getOptionValue(MainProcess.OPTION_VALIDITY);
		if (validityValue == null) {
			throw new IllegalArgumentException("Missing option -" + MainProcess.OPTION_VALIDITY);
		}
		Date validityDate = null;
		if (!validityValue.equals("null")) {
			try {
				validityDate = formatter.parse(validityValue);
			} catch (ParseException e) {
				throw new IllegalArgumentException(
				        "[Option -validity] Wrong date format, have to be " + DATE_FORMAT);
			}
		}

		// Get the multi st value if exists
		int multiStNbCoins = DEFAULT_MULTI_ST_NB_COINS;
		String multiStStr = cmd.getOptionValue(MainProcess.OPTION_MULTIST);
		if (multiStStr != null) {
			multiStNbCoins = Integer.parseInt(multiStStr);
		}

		// Get the multi st value if exists
		boolean auxContent = DEFAULT_AUX_CONTENT;
		if (cmd.hasOption(MainProcess.OPTION_AC)) {
			auxContent = true;
		}

		autoGenerate(dir, validityDate, multiStNbCoins, auxContent);
	}

	private void autoGenerate(String f, Date validityDate, int multiStNbCoins, boolean auxContent) {
		File file = new File(f);
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File child : file.listFiles()) {
					autoGenerate(child.getPath(), validityDate, multiStNbCoins, auxContent);
				}
			}
			if (file.isFile() && !file.isHidden()) {
				String licensePath = file.getParent() + "/acs.license";
				String keyPath = file.getPath();
				generateProcess.generateLicense(licensePath, keyPath, validityDate, multiStNbCoins,
				        auxContent);
			}
		}
	}

	@Override
	public void printHelp() {
		System.out.println("Auto Generate a license");
		System.out.println("\tRequired :");
		System.out.println("\t\t-action auto-gen");
		System.out.println("\t\t-dir root directory");
		System.out.println("\t\t-validity yyyy-MM-dd");
		System.out.println("\tOptional :");
		System.out.println("\t\t-multist n");
		System.out.println("\t\t-ac");
	}

}
