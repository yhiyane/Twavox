/**
 * Copyright (c) 2014 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.connector;

import com.dvidea.core.mediablock.plugin.IPlugin;
import com.dvidea.videoproj.gdc.plugin.GdcPlugin;

public class GdcConnector extends AbstractConnector {

	@Override
	public void init(String ip, int port, String login, String password)
	        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (plugin == null) {
			plugin = (IPlugin) Class.forName(GdcPlugin.class.getName()).newInstance();
		}
		plugin.init(ip, port, login, password, new String[0]);
	}

}
