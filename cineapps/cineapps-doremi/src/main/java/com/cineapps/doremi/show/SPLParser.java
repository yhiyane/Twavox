/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.doremi.show;

import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.cineapps.doremi.show.ShowPlaylist.EventList;
import com.cineapps.doremi.show.ShowPlaylist.EventList.Event;
import com.cineapps.doremi.show.ShowPlaylist.EventList.Event.ElementList.MainElement;
import com.cineapps.doremi.show.ShowPlaylist.EventList.Event.ElementList.MainElement.Composition;
import com.cineapps.doremi.show.ShowPlaylist.EventList.Event.ElementList.MainElement.Pattern;
import com.cineapps.mediablock.core.dcinema.SPLElement;
import com.cineapps.mediablock.core.dcinema.SPLInfos;

public class SPLParser {

	private static final Logger logger = Logger.getLogger(SPLParser.class);

	public static SPLInfos parse(String xml) throws ParseException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ShowPlaylist.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			ShowPlaylist showPlaylist = (ShowPlaylist) unmarshaller.unmarshal(reader);
			EventList eventList = showPlaylist.getEventList();
			if (eventList == null) {
				eventList = showPlaylist.getPackList().getPack().getEventList();
			}
			return parse(eventList);
		} catch (JAXBException e) {
			logger.error(e);
		}
		return null;
	}

	private static SPLInfos parse(EventList eventList) throws ParseException {
		if (eventList != null) {
			List<SPLElement> elements = new ArrayList<SPLElement>();

			for (Event event : eventList.getEvent()) {
				MainElement mainElement = event.getElementList().getMainElement();
				if (mainElement != null) {
					SPLElement splElement = null;
					Composition composition = mainElement.getComposition();
					if (composition != null) {
						splElement = elementFromComposition(composition);
					} else {
						splElement = elementFromPattern(mainElement.getPattern());
					}
					if (splElement == null) {
						throw new ParseException(
						        "Error : Parse the SPL without Composition or Pattern", 0);
					}
					elements.add(splElement);
				}
			}

			SPLInfos splInfos = new SPLInfos();
			splInfos.setElements(elements);
			return splInfos;
		} else {
			logger.error("Cannot parse an empty SPL");
		}
		return null;
	}

	private static SPLElement elementFromComposition(Composition composition) {
		SPLElement element = new SPLElement();

		return element;
	}

	private static SPLElement elementFromPattern(Pattern pattern) {
		SPLElement element = new SPLElement();

		return element;
	}
}
