/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.gdc.server;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import util.GdcCommand;

public class GdcUtils {

	private static final Logger logger = Logger.getLogger(GdcUtils.class.getName());

	/**
	 * Parse the xml request and returns the asked GdcCommand.
	 * 
	 * @param xmlRequest
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static GdcCommand parseCommand(String xmlRequest) throws ParserConfigurationException,
	        SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xmlRequest));
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();

		Element commandNode = (Element) doc.getElementsByTagName("command").item(0);
		String cmdAttribute = commandNode.getAttribute("cmd");
		GdcCommand gdcCommand = GdcCommand.valueOf(cmdAttribute);
		return gdcCommand;
	}
}
