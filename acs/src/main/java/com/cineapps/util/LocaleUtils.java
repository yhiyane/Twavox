/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.util.Locale;

import org.apache.log4j.Logger;

public class LocaleUtils {

	private static final Logger logger = Logger.getLogger(LocaleUtils.class);

	// @formatter:off
	/**
	 * Returns the ISO-639 value of the language in parameter. 
	 * If no correspondence is found, returns the parameter.
	 * 
	 * @param language
	 * @return
	 */
	// @formatter:on
	public static String getDisplayLanguage(String language) {
		try {
			Locale locale = new Locale.Builder().setLanguage(language).build();
			return locale.getDisplayLanguage(Locale.ENGLISH).toLowerCase();
		} catch (Exception e) {
			logger.error("Error when convert the language " + language + " to ISO-639");
		}
		return language;
	}
}
