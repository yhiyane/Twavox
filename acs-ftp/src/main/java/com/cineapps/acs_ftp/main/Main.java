/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.acs_ftp.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cineapps.acs_ftp.model.Ftp;
import com.cineapps.acs_ftp.sync.FtpSynchronizer;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		if (args.length == 1) {
			String jsonFilePath = args[0];
			JSONParser jsonParser = new JSONParser();

			try {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(jsonFilePath));

				// Extract the directory path
				String subtitlesDirPath = jsonObject.get("subtitlesDirPath").toString();

				// Extract the ftp server list
				List<Ftp> ftps = new ArrayList<>();
				JSONArray ftpObjs = (JSONArray) jsonObject.get("ftps");
				Iterator<JSONObject> iterator = ftpObjs.iterator();
				while (iterator.hasNext()) {
					JSONObject ftpObj = iterator.next();
					ftps.add(new Ftp(ftpObj.get("host").toString(), ftpObj.get("user").toString(),
					        ftpObj.get("password").toString(), ftpObj.get("path").toString()));
				}

				FtpSynchronizer.synchronize(subtitlesDirPath, ftps);

			} catch (IOException | ParseException e) {
				logger.error(e);
			}

		} else {
			throw new IllegalArgumentException("Expect a json file in argument");
		}
	}
}
