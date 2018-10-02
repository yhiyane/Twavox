/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VideoStreamWebService {

	private final String HOST = "localhost";
	private final int PORT = 8100;
	private final String STATUS_URL = "http://" + HOST + ":" + PORT + "/status";

	/**
	 * Consumes the Video Stream web service, method GET-/status. Returns the
	 * current position in the video in seconds and the status, or null if any
	 * error occurs.
	 * 
	 * @return
	 */
	public VideoStreamStatus getStatus() {
		HttpURLConnection con = null;
		try {
			URL obj = new URL(STATUS_URL);
			con = (HttpURLConnection) obj.openConnection();

			con.setConnectTimeout(0);
			con.setReadTimeout(1000);
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return VideoStreamStatus.fromString(response.toString());
		} catch (IOException e) {
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
}
