/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.gdc.sample;

import java.util.UUID;

import com.dvidea.core.mediablock.plugin.MainElement;
import com.dvidea.core.mediablock.plugin.PluginStatus;
import com.dvidea.core.mediablock.plugin.SPLElementInfos;
import com.dvidea.core.mediablock.plugin.SPLInfos;
import com.dvidea.videoproj.gdc.plugin.GdcPlugin;

public class SampleGdcClient {

	public static void main(String[] args) {
		GdcPlugin plugin = new GdcPlugin();
		plugin.init("localhost", 49153, "login", "password");

		PluginStatus pluginStatus = plugin.getStatus();
		System.out.println(pluginStatus);
		System.out.println(pluginStatus.getStatus());
		System.out.println(PluginStatus.STATUS_START);
		System.out.println(pluginStatus.getShowDuration());

		UUID plId = pluginStatus.getPlId();
		System.out.println(plId);
		SPLInfos splInfos = plugin.getSPL(plId);
		for (SPLElementInfos splElementInfos : splInfos.getContentList()) {
			MainElement mainElement = splElementInfos.getContent();
			System.out.println(mainElement.getName());
			System.out.println(mainElement.getEditableUnitDuration());
			System.out.println(mainElement.getEditRate());
		}
	}
}
