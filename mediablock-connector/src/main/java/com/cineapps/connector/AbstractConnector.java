/**
 * Copyright (c) 2014 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.connector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.cineapps.util.TimeUtils;
import com.dvidea.core.mediablock.plugin.CPLInfos;
import com.dvidea.core.mediablock.plugin.IPlugin;
import com.dvidea.core.mediablock.plugin.PluginStatus;
import com.dvidea.core.mediablock.plugin.SPLInfos;

abstract class AbstractConnector {

	protected IPlugin plugin;

	abstract public void init(String ip, int port, String login, String password)
	        throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	public void disconnect() {
		plugin.dispose();
	}

	public PluginStatus getStatus() {
		return plugin.getStatus();
	}

	public String getPlayingCplName() {
		PluginStatus pluginStatus = getStatus();

		// Retrieve the cpls
		Map<UUID, CPLInfos> cplMap = new HashMap<>();
		for (CPLInfos cplInfos : plugin.getCPLs()) {
			if (cplInfos != null) {
				cplMap.put(cplInfos.getId(), cplInfos);
			}
		}

		// Current spl uuid
		UUID splUuid = pluginStatus.getPlId();

		// Fetch with the current spl
		for (SPLInfos splInfos : plugin.getSPLs()) {
			if (splInfos.getUuid().compareTo(splUuid) == 0) {
				int preshowDuration_ms = 0;
				for (CPLInfos cpl : splInfos.getCpls()) {
					CPLInfos cplInfos = cplMap.get(cpl.getId());
					if (cplInfos != null) {
						String name = cplInfos.getName();
						// Its a feature, return the informations
						if (name.contains("FTR")) {
							System.out.println("Pre show duration : " + preshowDuration_ms);
							return name;
						}
						// Calculate the ms of this preShow element
						else {
							int editDuration = cplInfos.getEditableUnitDuration();
							int numerator = cplInfos.getEditRate().getNumerator();
							preshowDuration_ms += TimeUtils.editRateToMillis(editDuration,
							        numerator);
						}
					}

				}
				// We found the expected spl, stop the loop
				break;
			}
		}

		return null;
	}
}
