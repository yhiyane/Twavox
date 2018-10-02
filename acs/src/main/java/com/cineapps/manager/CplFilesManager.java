/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.manager;

import java.util.List;

import com.cineapps.model.cpl.CompositionPlaylist;

/**
 * Manager of the list of cpls for the same movie. Ex : It keeps the cpls
 * [MazeRunner_FTR_S_EN-FR_FR_, MazeRunner_FTR_S_FR-XX_FR_,
 * MazeRunner_FTR_S_FR-XX_OCAP_] for the movie Maze Runner
 */
public class CplFilesManager {

	private final String directoryPath;
	private final List<CompositionPlaylist> cpls;

	public CplFilesManager(String directoryPath, List<CompositionPlaylist> cpls) {
		this.directoryPath = directoryPath;
		this.cpls = cpls;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public List<CompositionPlaylist> getCpls() {
		return cpls;
	}

	/**
	 * Returns the cpl with the same content title text value, or null
	 * otherwise.
	 * 
	 * @param contentTitleText
	 * @return
	 */
	public CompositionPlaylist findByName(String contentTitleText) {
		if (cpls != null) {
			for (CompositionPlaylist cpl : cpls) {
				if (cpl.getContentTitleText().equals(contentTitleText)) {
					return cpl;
				}
			}
		}
		return null;
	}
}
