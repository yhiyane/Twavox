/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.dolby.sample;

import java.rmi.RemoteException;
import java.util.UUID;

import com.cineapps.dolby.mediablock.DolbyPlugin;
import com.cineapps.dolby.service.DolbyService;
import com.dolby.dcinema.ws.smi.v1.Fault;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ContentPlaybackState;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse;
import com.dvidea.core.mediablock.plugin.MainElement;
import com.dvidea.core.mediablock.plugin.PluginStatus;
import com.dvidea.core.mediablock.plugin.SPLElementInfos;
import com.dvidea.core.mediablock.plugin.SPLInfos;

public class SampleClient {

	private final String host;
	private final int port;
	private final DolbyService service;

	public SampleClient(String host, int port) {
		this.host = host;
		this.port = port;
		service = new DolbyService(host, port);
	}

	public String testPlayback() throws Fault {
		GetPlaybackStateResponse _getPlaybackState__return = service.getPlaybackState();
		ContentPlaybackState content = _getPlaybackState__return.getContentPlaybackState();

		System.out.println("Show duration : " + content.getShowDuration());
		System.out.println("Show position : " + content.getShowPosition());
		System.out.println("Clip position : " + content.getClipDuration());
		System.out.println("Content id : " + content.getContentId());

		return content.getContentId();
	}

	public void testShow(String contentId) throws Fault, RemoteException {
		GetSPLResponse _getSPL_return = service.getSPL(contentId);
		System.out.println("Show : " + _getSPL_return.getShow());
	}

	public void testPlugin() {
		DolbyPlugin plugin = new DolbyPlugin();
		plugin.init(host, port, null, null);

		PluginStatus pluginStatus = plugin.getStatus();
		System.out.println(pluginStatus);

		UUID splId = pluginStatus.getPlId();
		SPLInfos splInfos = plugin.getSPL(splId);
		int seconds = 0;
		for (SPLElementInfos splElementInfos : splInfos.getContentList()) {
			MainElement element = splElementInfos.getContent();
			System.out.println(element.getName() + " " + element.getEditableUnitDuration() + " "
			        + element.getEditRate().getNumerator());
			int s = element.getEditableUnitDuration() / element.getEditRate().getNumerator();
			seconds += s;
			System.out.println(s);
		}
		System.out.println("total : " + seconds);
	}

	public static void main(String[] args) throws Fault, RemoteException {
		// String host = "10.40.241.18";
		String host = "10.1.1.3";
		int port = 8080;
		SampleClient sample = new SampleClient(host, port);

		String contentId = sample.testPlayback();
		sample.testShow(contentId);

		sample.testPlugin();
	}
}
