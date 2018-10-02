/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class AuxiliaryContentUtils {

	private static final Logger logger = Logger.getLogger(AuxiliaryContentUtils.class);

	// @formatter:off
	/**
	 * Makes the poster and the synopsis visible through the mobile application. 
	 * 
	 * By convention, the mobile application can display the poster and the 
	 * synopsis if the files poster.jpg and synopsis.json are placed into a 
	 * specific path in the Twavox (posterUrl and synopsisUrl).
	 * 
	 * For a movie, we store the poster.jpg and synopsis.json files in 
	 * <i>auxContentDirUrl/moviePrefix/</i>
	 * 
	 * To make these files visible through the mobile application, we copy them to 
	 * the specific path <i>posterUrl</i> and <i>synopsisUrl</i>.
	 * 
	 * @param cplName : the complete cpl name
	 * @param auxContentDirUrl : the path to the root directory of all auxiliary files
	 * @param posterUrl : the specific path of the poster file
	 * @param synopsisUrl : the specific path of the synopsis file
	 */
	// @formatter:on
	public static void displayAuxiliaryContent(String cplName, String auxContentDirUrl,
	        String posterUrl, String synopsisUrl) {
		// Delete if exists
		hideAuxiliaryContent(posterUrl, synopsisUrl);

		auxContentDirUrl += auxContentDirUrl.endsWith("/") ? "" : "/";
		File rootDirectory = new File(auxContentDirUrl);

		// Create the root directory if not exists
		// This root directory should contain a sub-directory for each movie
		if (!rootDirectory.exists() && !rootDirectory.mkdirs()) {
			logger.info("Cannot create directory " + rootDirectory);
		}

		for (String movieDirectoryName : rootDirectory.list()) {
			if (cplName.toLowerCase().contains(movieDirectoryName.toLowerCase())) {
				String movieDirectoryPath = auxContentDirUrl + movieDirectoryName;
				movieDirectoryPath += movieDirectoryPath.endsWith("/") ? "" : "/";
				try {
					FileUtils.copyFile(new File(movieDirectoryPath + "poster.jpg"), new File(
					        posterUrl));
					FileUtils.copyFile(new File(movieDirectoryPath + "synopsis.json"), new File(
					        synopsisUrl));
				} catch (IOException e) {
					logger.error("Error when copying the auxiliary files");
					logger.error(e);
				}
				// No need to continue if we found a result
				break;
			}
		}
	}

	// @formatter:off
	/**
	 * Makes the poster and the synopsis invisible through the mobile application. 
	 * 
	 * By convention, the mobile application can display the poster and the 
	 * synopsis if the files poster.jpg and synopsis.json are placed into a 
	 * specific path in the Twavox (posterUrl and synopsisUrl).
	 * 
	 * To make these files invisible, we delete them from their specific paths.
	 * 
	 * @param posterUrl : the specific path of the poster file
	 * @param synopsisUrl : the specific path of the synopsis file
	 */
	// @formatter:on
	public static void hideAuxiliaryContent(String posterUrl, String synopsisUrl) {
		// Delete the poster
		File poster = new File(posterUrl);
		if (poster.exists() && !poster.delete()) {
			logger.error("Cannot delete the file " + posterUrl);
		}
		// Delete the synopsis
		File synopsis = new File(synopsisUrl);
		if (synopsis.exists() && !synopsis.delete()) {
			logger.error("Cannot delete the file " + synopsisUrl);
		}
	}
}
