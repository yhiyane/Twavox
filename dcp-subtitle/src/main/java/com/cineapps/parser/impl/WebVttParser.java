package com.cineapps.parser.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cineapps.parser.SubtitleParser;

public class WebVttParser extends SubtitleParser {

	@Override
	public void buildSubtitle(BufferedReader br, int delayMs) throws IOException {
		String strLine;
		String[] split;
		String timeIn;
		String timeOut;
		List<String> content = new ArrayList<String>();

		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			if (StringUtils.isNumeric(strLine) && !StringUtils.isBlank(strLine)) {
				// Subtitle number
				String number = strLine;
				// Times
				strLine = br.readLine();
				split = strLine.split(" --> ");
				timeIn = delayTime(split[0].replace(',', ':'), delayMs);
				timeOut = delayTime(split[1].replace(',', ':'), delayMs);
				// Content
				content.clear();
				while (StringUtils.isNotBlank(strLine = br.readLine())) {
					content.add(strLine);
				}
				super.addTextChild(number, timeIn, timeOut, content);
			}
		}
	}

}
