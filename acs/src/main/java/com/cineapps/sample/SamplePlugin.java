/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.sample;

import java.util.List;
import java.util.UUID;

import com.dvidea.core.mediablock.plugin.IPlugin;
import com.dvidea.core.mediablock.plugin.MainElement;
import com.dvidea.core.mediablock.plugin.PluginStatus;
import com.dvidea.core.mediablock.plugin.SPLElementInfos;
import com.dvidea.core.mediablock.plugin.SPLInfos;
import com.dvidea.videoproj.doremi.plugin.DoremiPlugin;

public class SamplePlugin {

	private final String host = "10.1.1.3";
	private final int port = 11730;
	private final String user = "manager";
	private final String pwd = "password";

	private void method1() {
		IPlugin plugin = new DoremiPlugin();
		plugin.init(host, port, user, pwd, new String[0]);

		/*** STATUS ***/
		System.out.println("*** STATUS ***");
		PluginStatus pluginStatus = plugin.getStatus();

		// Find the current spl
		UUID splUuid = pluginStatus.getPlId();
		SPLInfos spl = plugin.getSPL(splUuid);

		// Calculate the preshow duration
		if (spl != null) {
			List<SPLElementInfos> elements = spl.getContentList();
			System.out.println("Nb elements in the playlist : " + elements.size());
			for (SPLElementInfos element : elements) {
				MainElement el = element.getContent();
				int editDuration = el.getEditableUnitDuration();
				int numerator = el.getEditRate().getNumerator();
				int nbSeconds = editDuration / numerator;
				System.out.println(el.getName() + " " + nbSeconds);
			}
		} else {
			System.out.println("spl null");
		}
	}

	public static void main(String[] args) {
		SamplePlugin sample = new SamplePlugin();
		sample.method1();
	}
}
