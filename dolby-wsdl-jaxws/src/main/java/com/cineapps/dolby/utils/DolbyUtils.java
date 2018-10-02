/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.dolby.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.cineapps.dolby.show.ShowPlaylist;
import com.cineapps.dolby.show.ShowPlaylist.Show.CompositionPlaylistId;
import com.dvidea.core.Rate;
import com.dvidea.core.mediablock.plugin.CPLInfos;
import com.dvidea.core.mediablock.plugin.SPLElementInfos;
import com.dvidea.core.mediablock.plugin.SPLInfos;

public class DolbyUtils {

	private final static Logger logger = Logger.getLogger(DolbyUtils.class);
	private final static String UUID_PREFIX = "urn:uuid:";

	/**
	 * Converts a Javax Datatype Duration to seconds.
	 * 
	 * @param duration
	 * @return
	 */
	public static int durationToSeconds(javax.xml.datatype.Duration duration) {
		if (duration != null) {
			return duration.getHours() * 60 * 60 + duration.getMinutes() * 60
			        + (int) (duration.getSeconds());
		}
		return 0;
	}

	/**
	 * Converts a String uuid from Dolby to java.util.UUID.
	 * 
	 * @param dolbyUUID
	 * @return
	 */
	public static UUID toJavaUUID(String dolbyUUID) {
		String javaUUIDString = dolbyUUID.substring(UUID_PREFIX.length());
		return java.util.UUID.fromString(javaUUIDString);
	}

	/**
	 * Converts a java.util.UUID to Dolby String uuid.
	 * 
	 * @param javaUUID
	 * @return
	 * @throws MalformedURIException
	 */
	public static String toDolbyUUID(UUID javaUUID) {
		return UUID_PREFIX + javaUUID.toString();
	}

	/**
	 * Converts the show xml got from the Dolby Web Service to a SPLInfos.
	 * 
	 * @param showXml
	 * @return
	 */
	public static SPLInfos toSPLInfos(String showXml) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ShowPlaylist.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(showXml);
			ShowPlaylist showPlaylist = (ShowPlaylist) unmarshaller.unmarshal(reader);
			Rate defaultRate = new Rate(24);

			List<SPLElementInfos> elements = new ArrayList<SPLElementInfos>();
			for (Object o : showPlaylist.getShow().getMarkerOrCompositionPlaylistId()) {
				if (o instanceof CompositionPlaylistId) {
					CompositionPlaylistId compositionPlaylistId = (CompositionPlaylistId) o;
					CPLInfos cplInfos = new CPLInfos();
					cplInfos.setName(compositionPlaylistId.getTitle());
					cplInfos.setEditRate(defaultRate);
					cplInfos.setEditableUnitDuration(durationToSeconds(compositionPlaylistId
					        .getEstimatedDuration()) * defaultRate.getNumerator());
					SPLElementInfos element = new SPLElementInfos();
					element.setContent(cplInfos);
					elements.add(element);
				}
			}
			SPLInfos splInfos = new SPLInfos();
			splInfos.setContentList(elements);
			return splInfos;
		} catch (JAXBException e) {
			logger.error(e);
		}
		return null;
	}
}
