package com.cineapps.main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import com.cineapps.parser.SubtitleParser;
import com.cineapps.parser.impl.SrtParser;

public class Main {

	public static void main(String[] args) throws IOException, ParserConfigurationException {
		SubtitleParser parser = new SrtParser();
		String src = "/Users/macbookproapple/Desktop/in.srt";
		String dest = "/Users/macbookproapple/Desktop/out.xml";
		int delayMs = 0;
		parser.toDcpSubtitle(src, dest, delayMs);
		System.out.println("done");
	}
}
