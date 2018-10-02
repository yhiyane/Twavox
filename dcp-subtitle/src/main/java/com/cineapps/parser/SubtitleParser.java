package com.cineapps.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cineapps.util.XmlUtils;

public abstract class SubtitleParser {

	private Document doc;
	private BufferedReader br = null;
	private Element fonts;
	private String pattern = "HH:mm:ss:SSS";

	/**
	 * Initialize the xml dom structure with subtitle nodes following the
	 * official convention.
	 * 
	 * @throws Exception
	 */
	private void initXml() throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root element
		doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("DCSubtitle");
		doc.appendChild(rootElement);
		Attr attr = doc.createAttribute("Version");
		attr.setValue("1");
		rootElement.setAttributeNode(attr);
		// Subtitle ID
		Element subtitleId = doc.createElement("SubtitleID");
		subtitleId.appendChild(doc.createTextNode(UUID.randomUUID().toString()));
		rootElement.appendChild(subtitleId);
		// Movie title
		Element movieTitle = doc.createElement("MovieTitle");
		movieTitle.appendChild(doc.createTextNode("TITLE"));
		rootElement.appendChild(movieTitle);
		// Reel number
		Element reelNumber = doc.createElement("ReelNumber");
		reelNumber.appendChild(doc.createTextNode("1"));
		rootElement.appendChild(reelNumber);
		// Language
		Element language = doc.createElement("Language");
		language.appendChild(doc.createTextNode("French"));
		rootElement.appendChild(language);
		// Font
		fonts = doc.createElement("Font");
		attr = doc.createAttribute("Id");
		attr.setValue("Font1");
		fonts.setAttributeNode(attr);
		attr = doc.createAttribute("Color");
		attr.setValue("FFFFFFFF");
		fonts.setAttributeNode(attr);
		attr = doc.createAttribute("Effect");
		attr.setValue("border");
		fonts.setAttributeNode(attr);
		attr = doc.createAttribute("EffectColor");
		attr.setValue("FF000000");
		fonts.setAttributeNode(attr);
		attr = doc.createAttribute("Size");
		attr.setValue("42");
		fonts.setAttributeNode(attr);
		attr = doc.createAttribute("Weight");
		attr.setValue("normal");
		fonts.setAttributeNode(attr);
		rootElement.appendChild(fonts);
	}

	/**
	 * Append a subtitle child in the general xml dom structure.
	 * 
	 * @param number
	 * @param timeIn
	 * @param timeOut
	 * @param content
	 * @throws UnsupportedEncodingException
	 * @throws DOMException
	 */
	protected void addTextChild(String number, String timeIn, String timeOut, List<String> content)
	        throws DOMException, UnsupportedEncodingException {
		// Subtitle
		Element subtitle = doc.createElement("Subtitle");
		Attr attr = doc.createAttribute("SpotNumber");
		attr.setValue(number);
		subtitle.setAttributeNode(attr);
		attr = doc.createAttribute("TimeIn");
		attr.setValue(timeIn);
		subtitle.setAttributeNode(attr);
		attr = doc.createAttribute("TimeOut");
		attr.setValue(timeOut);
		subtitle.setAttributeNode(attr);
		attr = doc.createAttribute("FadeUpTime");
		attr.setValue("20");
		subtitle.setAttributeNode(attr);
		attr = doc.createAttribute("FadeDownTime");
		attr.setValue("20");
		subtitle.setAttributeNode(attr);

		for (String c : content) {
			// Text
			Element text = doc.createElement("Text");
			attr = doc.createAttribute("Direction");
			attr.setValue("horizontal");
			text.setAttributeNode(attr);
			attr = doc.createAttribute("HAlign");
			attr.setValue("center");
			text.setAttributeNode(attr);
			attr = doc.createAttribute("HPosition");
			attr.setValue("0.0");
			text.setAttributeNode(attr);
			attr = doc.createAttribute("VAlign");
			attr.setValue("bottom");
			text.setAttributeNode(attr);
			attr = doc.createAttribute("VPosition");
			attr.setValue("7.4");
			text.setAttributeNode(attr);
			// Font
			Element font = doc.createElement("Font");
			attr = doc.createAttribute("Color");
			attr.setValue("FFFFFFFF");
			font.setAttributeNode(attr);
			System.out.println(c);
			if (c.contains("<i>") || c.contains("</i>")) {
				c = c.replace("<i>", "");
				c = c.replace("</i>", "");
				attr = doc.createAttribute("Italic");
				attr.setValue("yes");
				font.setAttributeNode(attr);
			}
			font.appendChild(doc.createTextNode(new String(c.getBytes("UTF-8"), "UTF-8")));

			text.appendChild(font);
			subtitle.appendChild(text);
		}

		fonts.appendChild(subtitle);
	}

	public void toDcpSubtitle(String srcPath, String destPath, int delayMs) throws IOException {
		try {
			initXml();
		} catch (Exception e1) {
			System.err.println("Error on init");
			System.err.println(e1);
		}

		try {
			// Reader
			br = new BufferedReader(new InputStreamReader(new FileInputStream(srcPath), "UTF-8"));

			buildSubtitle(br, delayMs);

			XmlUtils.write(doc, destPath);
		} catch (Exception e) {
			System.err.println("Error on process");
			System.err.println(e);
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	public abstract void buildSubtitle(BufferedReader br, int delayMs) throws IOException;

	protected String delayTime(String time, int delayMs) {
		DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern(pattern));
		dateTime = dateTime.plusMillis(delayMs);
		return dateTime.toString(pattern);
	}
}
