/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli.CommandLine;

import com.cineapps.model.License;
import com.cineapps.service.LicenseService;

public class GenerateProcess implements Process {

	private final String DATE_FORMAT = "yyyy-MM-dd";
	private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	private final int DEFAULT_MULTI_ST_NB_COINS = -1;
	private final boolean DEFAULT_AUX_CONTENT = false;

	@Override
	public void execute(CommandLine cmd) {
		if (cmd != null) {
			// Get the license value
			String licenseValue = cmd.getOptionValue(MainProcess.OPTION_LICENSE);
			if (licenseValue == null) {
				throw new IllegalArgumentException("Missing option -" + MainProcess.OPTION_LICENSE);
			}

			// Get the key value
			String keyValue = cmd.getOptionValue(MainProcess.OPTION_KEY);
			if (keyValue == null) {
				throw new IllegalArgumentException("Missing option -" + MainProcess.OPTION_KEY);
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

			// Generate the license
			generateLicense(licenseValue, keyValue, validityDate, multiStNbCoins, auxContent);
		}
	}

	public void generateLicense(String licensePath, String keyFilePath, Date validityDate,
	        int multiStNbCoins, boolean auxContent) {
		// Generates the license
		LicenseService service = LicenseService.getService();
		License license = new License();
		license.setLimitDate(validityDate);
		license.setMultiStNbCoins(multiStNbCoins);
		license.setAuxContent(auxContent);
		license.setMac(service.readKey(keyFilePath));
		service.generateLicense(license, licensePath);

		System.out.println("License generated");
	}

	@Override
	public void printHelp() {
		System.out.println("Generate a license :");
		System.out.println("\tRequired :");
		System.out.println("\t\t-action generate");
		System.out.println("\t\t-license path/to/license");
		System.out.println("\t\t-key path/to/key");
		System.out.println("\t\t-validity yyyy-MM-dd");
		System.out.println("\tOptional :");
		System.out.println("\t\t-multist n");
		System.out.println("\t\t-ac");
	}

}
