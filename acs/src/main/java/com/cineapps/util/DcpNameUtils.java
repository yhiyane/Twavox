/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.util.Arrays;
import java.util.List;

public class DcpNameUtils {

	private final static List<String> hearingImpairedList = Arrays.asList("-OCAP", "-SME", "-HOH");

	// @formatter:off
	/**
	 * Returns the movie name from the dcp name.
	 * We used the dcp naming convention for that.
	 * 
	 * @param dcpName
	 * @return
	 */
	// @formatter:on
	public static String getMovieName(String dcpName) {
		return dcpName.split("_")[0];
	}

	// @formatter:off
	/**
	 * Returns a Boolean object to determines if the dcp is a VI version :
	 *  - null if the dcp is null
	 *  - true/false depending on the VI presence
	 * We used the dcp naming convention for that.
	 * 
	 * @param dcpName
	 * @return
	 */
	// @formatter:on
	public static Boolean isVIVersion(String dcpName) {
		if (dcpName == null) {
			return null;
		}
		String movieName = getMovieName(dcpName);
		String metadata = dcpName.substring(movieName.length());
		if (metadata.contains("-VI")) {
			return true;
		}
		return false;
	}

	// @formatter:off
	/**
	 * Returns true if the cplName is for Hearing Impaired, false otherwise.
	 * By the dcp naming convention, we can identify a hearing impaired cpl by the
	 * presence of a special tag in the name.
	 * 
	 * @param dcpName : the dcp name
	 * @return
	 */
	// @formatter:on
	public static boolean isForHearingImpaired(String dcpName) {
		if (dcpName != null) {
			String movieName = getMovieName(dcpName);
			String metadata = dcpName.substring(movieName.length());
			for (String hearingImpaired : hearingImpairedList) {
				if (metadata.toLowerCase().contains(hearingImpaired.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}
}
