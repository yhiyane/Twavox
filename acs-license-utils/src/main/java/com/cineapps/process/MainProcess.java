/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.process;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.cineapps.model.Action;

public class MainProcess implements Process {

	private final Options options;
	public static final String OPTION_ACTION = "action";
	public static final String OPTION_HELP = "help";
	public static final String OPTION_LICENSE = "license";
	public static final String OPTION_KEY = "key";
	public static final String OPTION_VALIDITY = "validity";
	public static final String OPTION_MULTIST = "multist";
	public static final String OPTION_AC = "ac";
	public static final String OPTION_DIR = "dir";

	private final GenerateProcess generateProcess = new GenerateProcess();
	private final ReadProcess readProcess = new ReadProcess();
	private final AutoGenProcess autoGenProcess = new AutoGenProcess();
	private final AutoReadProcess autoReadProcess = new AutoReadProcess();

	public MainProcess() {
		options = new Options();
		options.addOption(OPTION_ACTION, true, "Action to do");
		options.addOption(OPTION_HELP, false, "help");
		options.addOption(OPTION_LICENSE, true, "[Required] path to the license");
		options.addOption(OPTION_KEY, true, "[Required] path to the key");
		options.addOption(OPTION_VALIDITY, true, "[Required] validity date");
		options.addOption(OPTION_MULTIST, true, "[Optional] active MULTI_ST option");
		options.addOption(OPTION_AC, false, "[Optional] active AC option");
		options.addOption(OPTION_DIR, true, "[Required] for auto generating license");
	}

	public void execute(String[] args) {
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		execute(cmd);
	}

	@Override
	public void execute(CommandLine cmd) {
		// ACTION case
		String actionValue = cmd.getOptionValue(OPTION_ACTION);
		if (actionValue != null) {
			Action action = Action.fromString(actionValue);
			if (action != null) {
				switch (action) {
					case GENERATE:
						generateProcess.execute(cmd);
						break;
					case READ:
						readProcess.execute(cmd);
						break;
					case AUTOGEN:
						autoGenProcess.execute(cmd);
						break;
					case AUTOREAD:
						autoReadProcess.execute(cmd);
						break;
				}
			} else {
				throw new IllegalArgumentException("Invalid '-action' option ");
			}
		}

		// HELP case
		if (cmd.hasOption(OPTION_HELP) || actionValue == null) {
			printHelp();
		}
	}

	@Override
	public void printHelp() {
		System.out.println("### Help : ");
		generateProcess.printHelp();
		readProcess.printHelp();
	}
}
