/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cineapps.model.cpl.CompositionPlaylist;
import com.cineapps.model.subtitle.SubtitleInfo;

public class SubtitleUtils {

	private static final Logger logger = Logger.getLogger(SubtitleUtils.class);

	// @formatter:off
	/**
	 * Parses the subtitle file and extracts the list of subtitle nodes, or
	 * an empty list if any error occurred.
	 * 
	 * @param subtitleFilePath
	 * @return the list of subtitle element containing in the file, or an empty
	 *         list if the file does not exist
	 */
	// @formatter:on
	public static List<Node> extractSubtitles(String subtitleFilePath) {
		File subtitleFile = new File(subtitleFilePath);
		if (!subtitleFile.exists()) {
			logger.error("The file " + subtitleFilePath + " does not exist");
			return Collections.emptyList();
		}
		List<Node> subtitles = new ArrayList<>();
		try {
			// Parse the file
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(subtitleFile);
			NodeList subtitleNodes = document.getElementsByTagName("Font").item(0).getChildNodes();

			// Convert NodeList to list of Node
			if (subtitleNodes != null) {
				for (int i = 0; i < subtitleNodes.getLength(); i++) {
					Node item = subtitleNodes.item(i);
					if (item instanceof Element) {
						subtitles.add(item);
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			logger.error("Error when parsing the subtitle file " + subtitleFilePath);
			logger.error(e);
		}
		return subtitles;
	}

	// @formatter:off
	/**
	 * Parses the subtitle file and returns the language value, or null if any
	 * error occurred.
	 * 
	 * @param subtitleFilePath
	 * @return
	 */
	// @formatter:on
	public static String extractLanguage(String subtitleFilePath) {
		File subtitleFile = new File(subtitleFilePath);
		if (!subtitleFile.exists()) {
			logger.error("The file " + subtitleFilePath + " does not exist");
			return null;
		}
		try {
			// Parse the file
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(subtitleFile);
			String language = document.getElementsByTagName("Language").item(0).getTextContent();
			language = LocaleUtils.getDisplayLanguage(language);
			logger.info("lang found : " + language);
			return language;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			logger.error("Error when parsing the subtitle file " + subtitleFilePath);
			logger.error(e);
		}
		return null;
	}

	// @formatter:off
	/**
	 * Transform a Node into a String or null if any error occurred.
	 * 
	 * @param subtitleNode
	 * @return
	 */
	// @formatter:on
	public static String nodeToString(Transformer transformer, Node subtitleNode) {
		try {
			if (subtitleNode != null && transformer != null) {
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(subtitleNode), new StreamResult(writer));
				String string = writer.toString();
				string = string.replaceAll("[\n\t]", "");
				return string;
			} else {
				logger.error("Node or Transformer is null");
			}
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			logger.error("Cannot convert node to string");
			logger.error(e);
		}
		return null;
	}

	// @formatter:off
	/**
	 * Returns the list of subtitles from the list of cpls.
	 * Each cpl is scanned to get the informations about the possible subtitles 
	 * (languages, files path, port, ...)
	 * If a cpl has not subtitle, it is excluded from the returned list.
	 * 
	 * @param rootDirectory
	 * 			  the path of the root subtitle directory 
	 * 			  (ex : "/subtitles/Divergente/")
	 * @param cpls
	 *            the list of cpls in the root directory
	 * @param currentCpl
	 *            the cpl of the current playing dcp
	 * @param isMultiSubtitleActivate
	 *            true if the multi-subtitle option is activated
	 * @param freeLanguages
	 *            the list of free languages
	 * @param defaultPort
	 *            the default port for the HI people
	 * 
	 * @return a list with the informations on the available subtitles
	 */
	// @formatter:on
	public static List<SubtitleInfo> prepareSubtitleInfo(String rootDirectory,
	        List<CompositionPlaylist> cpls, CompositionPlaylist currentCpl,
	        boolean isMultiSubtitleActivate, List<String> freeLanguages, int defaultPort) {
		// Initialize the variables
		rootDirectory += rootDirectory.endsWith("/") ? "" : "/";
		SubtitleInfo hearingImpairedSubtitleInfo = null;
		Map<String, List<SubtitleInfo>> mapLangSubtitleInfos = new HashMap<>();
		List<SubtitleInfo> result = new ArrayList<>();

		// Sorts the cpls by language
		for (CompositionPlaylist cpl : cpls) {
			List<SubtitleInfo> subtitleInfos = CplUtils.generateSubtitleInfo(rootDirectory, cpl,
			        currentCpl);
			if (subtitleInfos != null) {
				for (SubtitleInfo subtitleInfo : subtitleInfos) {
					// If is for HI, store it in variable
					if (hearingImpairedSubtitleInfo == null
					        && DcpNameUtils.isForHearingImpaired(cpl.getContentTitleText())) {
						hearingImpairedSubtitleInfo = subtitleInfo;
					}
					// Else, put it in the map sorted by the language
					else {
						String key = subtitleInfo.getLang();
						List<SubtitleInfo> localizedSubtitleInfos = mapLangSubtitleInfos.get(key);
						if (localizedSubtitleInfos == null) {
							localizedSubtitleInfos = new ArrayList<>();
							localizedSubtitleInfos.add(subtitleInfo);
							mapLangSubtitleInfos.put(key, localizedSubtitleInfos);
						} else {
							localizedSubtitleInfos.add(subtitleInfo);
						}
					}
				}

			}
		}

		// Add the subtitleInfo for the hearing impaired
		if (hearingImpairedSubtitleInfo != null) {
			hearingImpairedSubtitleInfo.setPort(defaultPort);
			hearingImpairedSubtitleInfo.setOptional(false);
			result.add(hearingImpairedSubtitleInfo);
			// We remove the others subtitleInfo with the same language
			mapLangSubtitleInfos.remove(hearingImpairedSubtitleInfo.getLang());
		}

		int i = 0;
		for (Entry<String, List<SubtitleInfo>> entry : mapLangSubtitleInfos.entrySet()) {
			String lang = entry.getKey();
			// For each language, we get the longest subtitleInfo
			SubtitleInfo longuestSubtitleInfo = getLonguest(entry.getValue());
			// Free language
			if (freeLanguages.contains(lang.toLowerCase())) {
				// If there is no subtitleInfo for HI, we choose the first free
				// subtitleInfo
				if (hearingImpairedSubtitleInfo == null) {
					longuestSubtitleInfo.setPort(defaultPort);
					// Mark it as not null
					hearingImpairedSubtitleInfo = longuestSubtitleInfo;
				} else {
					longuestSubtitleInfo.setPort(defaultPort + i + 1);
				}
				longuestSubtitleInfo.setOptional(false);
				result.add(longuestSubtitleInfo);
				i++;
			} else if (isMultiSubtitleActivate) {
				longuestSubtitleInfo.setPort(defaultPort + i + 1);
				longuestSubtitleInfo.setOptional(true);
				result.add(longuestSubtitleInfo);
				i++;
			}
		}

		return result;
	}

	// @formatter:off
	/**
	 * Returns the subtitleInfo with the longest total files length among a list, 
	 * or null if the list is empty.
	 * 
	 * @param subtitleInfos
	 * @return
	 */
	// @formatter:on
	private static SubtitleInfo getLonguest(List<SubtitleInfo> subtitleInfos) {
		if (subtitleInfos != null && !subtitleInfos.isEmpty()) {
			Collections.sort(subtitleInfos);
			return subtitleInfos.get(0);
		}
		return null;
	}
}
