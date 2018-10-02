/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.cineapps.manager.CplFilesManager;
import com.cineapps.model.cpl.CompositionPlaylist;

public class FileSystemUtils {

	private static final Logger logger = Logger.getLogger(FileSystemUtils.class);

	// @formatter:off
	/**
	 * Returns the list of cpls in the directory, or null if no cpl is found.
	 * Scans the root level of the directory (not in sub-directory) and looks for
	 * xml files only.
	 * By convention, we transfer cpl and subtitle files that are in xml format.
	 * Cpl files are in the root level, and each subtitle file is in a sub-directory.
	 * 
	 * @param destPath
	 *            : path to the cpls files like "/subtitles/VacPetitNicolas/".
	 * @return
	 */
	// @formatter:on
	public static CplFilesManager findCplFiles(String destPath) {
		File dir = new File(destPath);
		if (dir.isDirectory() && dir.exists()) {
			File[] cplFiles = dir.listFiles(new FileFilter() {

				@Override
				public boolean accept(File file) {
					// Returns all .xml files
					return file.isFile() && file.getName().endsWith("xml");
				}
			});
			List<CompositionPlaylist> cpls = new ArrayList<>();
			for (File file : cplFiles) {
				CompositionPlaylist cpl = CplUtils.parseFromFile(file.getAbsolutePath());
				if (cpl != null) {
					cpls.add(cpl);
				}
			}
			logger.info("Found " + cpls.size() + " cpl files in " + destPath);
			return new CplFilesManager(destPath, cpls);
		}
		logger.info("No directory in " + destPath);
		return null;
	}

	// @formatter:off
	/**
	 * Returns the list of files name in the directory sorted by name, 
	 * or null if the directory does not exist.
	 * 
	 * @param directoryPath
	 * @return
	 */
	// @formatter:on
	public static List<String> getList(String directoryPath) {
		File directory = new File(directoryPath);
		if (directory.exists()) {
			List<String> list = Arrays.asList(directory.list());
			Collections.sort(list);
			return list;
		}
		return null;
	}

	// @formatter:off
	/**
	 * Returns the percent of the free disk space.
	 * 
	 * @return
	 */
	// @formatter:on
	public static int getAvailablePercent(String path) {
		File file = new File(path);
		long totalSpace = file.getTotalSpace(); // total disk space in bytes.
		long freeSpace = file.getFreeSpace(); // free disk space in bytes.
		return Math.round(((float) freeSpace / (float) totalSpace) * 100);
	}

	// @formatter:off
	/**
	 * Deletes a directory. 
	 * Returns true if the deletion succeeded, false otherwise.
	 * 
	 * @param directoryPath
	 * @return
	 */
	// @formatter:on
	public static boolean deleteDirectory(String directoryPath) {
		try {
			FileUtils.deleteDirectory(new File(directoryPath));
			return true;
		} catch (IOException e) {
			logger.error(e);
		}
		return false;
	}
}
