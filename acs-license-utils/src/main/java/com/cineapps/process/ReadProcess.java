/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.process;

import org.apache.commons.cli.CommandLine;

import com.cineapps.model.License;
import com.cineapps.service.LicenseService;

public class ReadProcess implements Process {

	@Override
	public void execute(CommandLine cmd) {
		if (cmd != null) {
			// Get the license value
			String licenseValue = cmd.getOptionValue(MainProcess.OPTION_LICENSE);
			if (licenseValue == null) {
				throw new IllegalArgumentException("Missing option -" + MainProcess.OPTION_LICENSE);
			}
			// Print the license
			readLicense(licenseValue);

			// Print the key if wanted
			String keyValue = cmd.getOptionValue(MainProcess.OPTION_KEY);
			if (keyValue != null) {
				readKey(keyValue);
			}
		}
	}

	public void readLicense(String licensePath) {
		LicenseService service = LicenseService.getService();
		service.loadLicense(licensePath);
		License license = service.getLicense();

		if (license != null) {
			System.out.println("LICENSE ##### " + licensePath);
			System.out.println("mac : " + license.getMac());
			System.out.println("limit : " + license.getLimitDate());
			System.out.println("multiSt : " + license.getMultiStNbCoins());
			System.out.println("auxContent : " + license.isAuxContent());
			System.out.println("valid : " + license.isValid());
			System.out.println();
		} else {
			System.out.println("Wrong license file");
		}
	}

	public void readKey(String keyPath) {
		LicenseService service = LicenseService.getService();
		String mac = service.readKey(keyPath);
		System.out.println("KEY ##### " + keyPath);
		System.out.println("mac : " + mac);
		System.out.println();
	}

	@Override
	public void printHelp() {
		System.out.println("Read a license :");
		System.out.println("\tRequired :");
		System.out.println("\t\t-action read");
		System.out.println("\t\t-license path/to/license");
		System.out.println("\t\t-key path/to/key");
	}

}
