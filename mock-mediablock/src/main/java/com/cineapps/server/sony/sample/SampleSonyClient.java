/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.sony.sample;

import java.util.UUID;

import com.dvidea.core.mediablock.plugin.MainElement;
import com.dvidea.core.mediablock.plugin.PluginStatus;
import com.dvidea.core.mediablock.plugin.SPLElementInfos;
import com.dvidea.core.mediablock.plugin.SPLInfos;
import com.dvidea.videoproj.sony.plugin.SonyPlugin;

public class SampleSonyClient {

	public static void main(String[] args) {
		SonyPlugin plugin = new SonyPlugin();
		plugin.init("10.1.1.3", 4433, "login", "password");

		PluginStatus pluginStatus = plugin.getStatus();
		System.out.println(pluginStatus);

		UUID splId = pluginStatus.getPlId();
		System.out.println(splId);
		SPLInfos splInfos = plugin.getSPL(splId);
		for (SPLElementInfos splElementInfos : splInfos.getContentList()) {
			MainElement mainElement = splElementInfos.getContent();
			System.out.println(mainElement.getName());
			System.out.println(mainElement.getEditableUnitDuration());
			System.out.println(mainElement.getEditRate());
		}
	}
}
