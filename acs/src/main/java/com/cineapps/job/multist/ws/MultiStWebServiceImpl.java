/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.job.multist.ws;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cineapps.model.subtitle.SubtitleInfo;
import com.cineapps.service.LicenseService;
import com.cineapps.service.MultiStService;
import com.cineapps.service.ServicesHolder;

public class MultiStWebServiceImpl implements MultiStWebService {

	@SuppressWarnings("unchecked")
	@Override
	public String getCurrentPlayedSubtitles() {
		ServicesHolder servicesHolder = ServicesHolder.getInstance();
		MultiStService multiStService = servicesHolder.getMultiStService();
		LicenseService licenseService = servicesHolder.getLicenseService();

		JSONObject json = new JSONObject();
		String sessionUuid = multiStService.getSessionUuid() == null ? "" : multiStService
		        .getSessionUuid().toString();
		json.put("uuid", sessionUuid);
		json.put("unit", licenseService.getLicense().getMultiStNbCoins());

		JSONArray subtitleArray = new JSONArray();
		for (SubtitleInfo subtitleInfo : multiStService.getCurrentPlayedSubtitles()) {
			JSONObject subtitle = new JSONObject();
			subtitle.put("Language", subtitleInfo.getLang());
			subtitle.put("Port", subtitleInfo.getPort());
			subtitle.put("Optional", subtitleInfo.isOptional());
			subtitleArray.add(subtitle);
		}
		json.put("subtitles", subtitleArray);

		return json.toJSONString();
	}

}
