/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cineapps.model.cpl.CompositionPlaylist;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel.AssetList;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel.AssetList.MainSound;
import com.cineapps.model.cpl.CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle;
import com.cineapps.model.subtitle.SubtitleFile;
import com.cineapps.model.subtitle.SubtitleInfo;

public class CplUtils {

	private static final Logger logger = Logger.getLogger(CplUtils.class);
	private static final long MIN_SUBTITLE_FILES_LENGTH_BYTES = 50000;

	// @formatter:off
	/**
	 * Parses a cpl file.
	 * 
	 * @param cplPath
	 *            : the path to the cpl file to parse.
	 * @return
	 */
	// @formatter:on
	public static CompositionPlaylist parseFromFile(String cplPath) {
		return CompositionPlaylist.fromFile(cplPath);
	}

	// @formatter:off
	/**
	 * Returns the informations about the subtitles in the cpl.
	 * 
	 * The cpl parameter is the cpl to scan.
	 * The playingCpl parameter is the current played cpl.
	 * Those two cpls should be related (same movie but different version).
	 * We need the playing cpl to synchronize it with the cpl to scan, that can
	 * contains additional scenes at the beginning.
	 * For instance, the English version of a lambda movie can have 5 seconds 
	 * in addition to introduce a partner, that is not present in the French version.
	 * 
	 * A standard or OCAP cpl returns 0 or 1 SubtitleInfo instance.
	 * A CCAP cpl returns 0 or n SubtitleInfo instances.
	 * 
	 * @param directoryPath
	 * @param cpl
	 * @param playingCpl
	 * @return
	 */
	// @formatter:on
	public static List<SubtitleInfo> generateSubtitleInfo(String directoryPath,
	        CompositionPlaylist cpl, CompositionPlaylist playingCpl) {
		directoryPath += directoryPath.endsWith("/") ? "" : "/";

		// CCAP case
		if (cpl.getContentTitleText().contains("CCAP")) {
			return generateSubtitleInfoForCcapCpl(directoryPath, cpl, playingCpl);
		}
		// Standard cpl case
		List<SubtitleInfo> subtitleInfos = null;
		SubtitleInfo subtitleInfo = generateSubtitleInfoForNotCcapCpl(directoryPath, cpl,
		        playingCpl);
		if (subtitleInfo != null) {
			subtitleInfos = new ArrayList<>();
			subtitleInfos.add(subtitleInfo);
		}
		return subtitleInfos;
	}

	// @formatter:off
	/**
	 * Extracts all the informations about the subtitle files (the language, the
	 * sequence of the xml files, the total files size, ...). 
	 * This method has to be called for not ccap cpl (a cpl with at most one subtitle language)
	 * 
	 * @param directoryPath
	 * @param cpl
	 * @param playingCpl
	 * @return
	 */
	// @formatter:on
	private static SubtitleInfo generateSubtitleInfoForNotCcapCpl(String directoryPath,
	        CompositionPlaylist cpl, CompositionPlaylist playingCpl) {
		int editRateDurationFromStart = 0;
		List<SubtitleFile> subtitleFiles = new ArrayList<>();
		long totalFilesLength = 0;
		int editRateNumerator = -1;

		// Loop on reels
		for (Reel reel : cpl.getReelList().getReel()) {
			AssetList assetList = reel.getAssetList();
			List<MainSubtitle> mainSubtitles = assetList.getMainSubtitle();
			if (!mainSubtitles.isEmpty()) {
				MainSubtitle mainSubtitle = mainSubtitles.get(0);
				// Get the uuid corresponding to the name of the directory of
				// the subtitle file
				String id = mainSubtitle.getId();
				String uuid = id.substring(id.length() - 36);
				String subtitlePath = directoryPath + uuid;

				// Looking for the subtitle file
				File directory = new File(subtitlePath);
				if (directory.exists()) {
					for (File file : directory.listFiles()) {
						// Get the first xml file that has to be the subtitle
						// file
						if (file.getName().endsWith("xml")) {
							// Get the edit rate
							if (editRateNumerator == -1) {
								editRateNumerator = Integer.parseInt(mainSubtitle.getEditRate()
								        .split(" ")[0]);
							}
							int editRateDuration = mainSubtitle.getDuration();
							// Add a new SubtitleFile
							subtitleFiles.add(new SubtitleFile(subtitlePath + "/" + file.getName(),
							        editRateDurationFromStart, editRateNumerator));
							// Update the counter
							editRateDurationFromStart += editRateDuration;
							totalFilesLength += file.length();
							break;
						}
					}
				} else {
					logger.error("Missing directory at " + subtitlePath);
					return null;
				}
			}
			// In this case, we have a blank between two reels
			else if (!subtitleFiles.isEmpty()) {
				MainSound mainSound = assetList.getMainSound();
				editRateDurationFromStart += mainSound.getDuration();
			}
		}
		// Remove subtitle files with insufficient size
		// These subtitles are for foreign languages scenes - not interesting
		if (totalFilesLength < MIN_SUBTITLE_FILES_LENGTH_BYTES) {
			logger.info("Reject " + cpl.getContentTitleText() + " - Insufficient size : "
			        + totalFilesLength + " bytes");
			return null;
		}

		// Calculate the pre movie duration
		int preMovieDurationMillis = getPreMovieDurationMillis(cpl, playingCpl, editRateNumerator);

		// Get the language value in the last reel
		// Be careful, the first reel language can be wrong (see
		// MazeRunner/4dd...)
		String lastSubtitleFilePath = subtitleFiles.get(subtitleFiles.size() - 1).getPath();
		String lang = SubtitleUtils.extractLanguage(lastSubtitleFilePath);
		if (lang == null) {
			logger.error("Cannot determine the language in the file " + lastSubtitleFilePath);
			lang = "Unknown";
		}

		return new SubtitleInfo(cpl.getContentTitleText(), subtitleFiles, preMovieDurationMillis,
		        totalFilesLength, lang);
	}

	// @formatter:off
	/**
	 * Extracts all the informations about the subtitle files (the language, the
	 * sequence of the xml files, the total files size, ...). 
	 * This method has to be called for CCAP cpl.
	 * 
	 */
	// @formatter:on
	private static List<SubtitleInfo> generateSubtitleInfoForCcapCpl(String directoryPath,
	        CompositionPlaylist cpl, CompositionPlaylist playingCpl) {
		Map<String, List<SubtitleFile>> subtitleFilesMap = new HashMap<>();
		int editRateDurationFromStart = 0;
		long totalFilesLength = 0;
		int editRateNumerator = -1;
		int localDuration = 0;
		long localFileLength = 0;

		// Loop on reels
		for (Reel reel : cpl.getReelList().getReel()) {
			AssetList assetList = reel.getAssetList();
			List<MainSubtitle> mainSubtitles = assetList.getMainSubtitle();

			if (!mainSubtitles.isEmpty()) {
				for (MainSubtitle mainSubtitle : mainSubtitles) {
					// Get the uuid corresponding to the name of the directory
					// of the subtitle file
					String id = mainSubtitle.getId();
					String uuid = id.substring(id.length() - 36);
					String subtitlePath = directoryPath + uuid;

					// Get the language
					// For CCAP cpl, the language value has to exist
					String lang = mainSubtitle.getLanguage();
					if (lang == null) {
						logger.error("The CCAP cpl has no language value for the subtitles");
						return null;
					}
					List<SubtitleFile> subtitleFiles = subtitleFilesMap.get(lang);
					if (subtitleFiles == null) {
						subtitleFiles = new ArrayList<>();
						subtitleFilesMap.put(lang, subtitleFiles);
					}

					// Looking for the subtitle file
					File directory = new File(subtitlePath);
					if (directory.exists()) {
						for (File file : directory.listFiles()) {
							// Get the first xml file that has to correspond
							// to a subtitle file
							if (file.getName().endsWith("xml")) {
								// Get the edit rate
								if (editRateNumerator == -1) {
									editRateNumerator = Integer.parseInt(mainSubtitle.getEditRate()
									        .split(" ")[0]);
								}
								// Add a new SubtitleFile
								subtitleFiles.add(new SubtitleFile(subtitlePath + "/"
								        + file.getName(), editRateDurationFromStart,
								        editRateNumerator));
								// Update the counter
								localDuration = mainSubtitle.getDuration();
								localFileLength = file.length();
								break;
							}
						}
					} else {
						logger.error("Missing directory at " + subtitlePath);
						return null;
					}
				}
				editRateDurationFromStart += localDuration;
				totalFilesLength += localFileLength;
			}
			// In this case, we have a blank between two reels
			else if (!subtitleFilesMap.isEmpty()
			        && !subtitleFilesMap.values().iterator().next().isEmpty()) {
				MainSound mainSound = assetList.getMainSound();
				editRateDurationFromStart += mainSound.getDuration();
			}

		}

		// Remove subtitle files with insufficient size
		// These subtitles are for foreign languages scenes - not interesting
		if (totalFilesLength < MIN_SUBTITLE_FILES_LENGTH_BYTES) {
			logger.info("Reject " + cpl.getContentTitleText() + " - Insufficient size : "
			        + totalFilesLength + " bytes");
			return null;
		}

		// Calculate the pre movie duration
		int preMovieDurationMillis = getPreMovieDurationMillis(cpl, playingCpl, editRateNumerator);

		// Returns the list of results
		List<SubtitleInfo> subtitleInfos = new ArrayList<>();
		for (String lang : subtitleFilesMap.keySet()) {
			SubtitleInfo subtitleInfo = new SubtitleInfo(cpl.getContentTitleText(),
			        subtitleFilesMap.get(lang), preMovieDurationMillis, totalFilesLength, lang);
			subtitleInfos.add(subtitleInfo);
		}
		return subtitleInfos;
	}

	// @formatter:off
	/**
	 * Returns the pre-movie duration in millisecond. 
	 * This duration is the difference between the playing cpl and the cpl 
	 * with subtitles to process.
	 * This difference is used to introduce a partner at the beginning of the movie.
	 * 
	 */
	// @formatter:on
	private static int getPreMovieDurationMillis(CompositionPlaylist cplWithSub,
	        CompositionPlaylist playingCpl, int editRateNumerator) {
		List<Reel> playingReels = playingCpl.getReelList().getReel();
		List<Reel> subReels = cplWithSub.getReelList().getReel();
		int sumPlayingEditDuration = 0;
		int sumSubEditDuration = 0;
		int totalDurationWithSub = 0;
		int i = 0;

		for (Reel subReel : subReels) {
			AssetList subAssetList = subReel.getAssetList();
			MainSound subMainSound = subAssetList.getMainSound();
			totalDurationWithSub += subMainSound.getDuration();

			if (!subAssetList.getMainSubtitle().isEmpty()) {
				sumSubEditDuration += subMainSound.getDuration();

				for (; i < playingReels.size(); i++) {
					Reel playingReel = playingReels.get(i);
					MainSound playingMainSound = playingReel.getAssetList().getMainSound();
					sumPlayingEditDuration += playingMainSound.getDuration();

					if (subMainSound.getDuration() == playingMainSound.getDuration()) {
						int preMovieEditDuration = sumPlayingEditDuration - sumSubEditDuration;
						return TimeUtils.editRateToMillis(preMovieEditDuration, editRateNumerator);
					}

					if (sumPlayingEditDuration > totalDurationWithSub) {
						// continue with the next element in the subAssetList
						sumPlayingEditDuration -= playingMainSound.getDuration();
						break;
					}
				}
			}
		}

		// Returns the delta between the playing cpl and the cpl with subtitles
		int diff = sumPlayingEditDuration - totalDurationWithSub;
		return TimeUtils.editRateToMillis(diff, editRateNumerator);
	}
}