/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.dolby.sample;

import java.net.URL;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.cineapps.dolby.mediablock.DolbyPlugin;
import com.cineapps.mediablock.core.dcinema.PluginStatus;
import com.cineapps.mediablock.core.dcinema.SPLElement;
import com.cineapps.mediablock.core.dcinema.SPLInfos;
import com.dolby.dcinema.ws.smi.v1.Fault;
import com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV10;
import com.dolby.dcinema.ws.smi.v1.PlaybackControlV10;
import com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV10;
import com.dolby.dcinema.ws.smi.v1.ShowManagementV10;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ContentPlaybackState;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse;

public class SampleClient {

	private final String host;
	private static final QName PLAYBACK_SERVICE_NAME = new QName(
	        "http://www.dolby.com/dcinema/ws/smi/v1", "PlaybackControl_v1_0");
	private static final QName SHOW_SERVICE_NAME = new QName(
	        "http://www.dolby.com/dcinema/ws/smi/v1", "ShowManagement_v1_0");

	public SampleClient(String host) {
		this.host = host;
	}

	public String testPlayback() throws Fault {
		URL wsdlURL = PlaybackControlV10.WSDL_LOCATION;
		PlaybackControlV10 ss = new PlaybackControlV10(wsdlURL, PLAYBACK_SERVICE_NAME);
		PlaybackControlServiceV10 port = ss.getPlaybackControlServiceV10();

		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		        String.format("http://%s:8080/dcinema/ws/smi/v1/PlaybackControlService", host));

		GetPlaybackStateRequest _getPlaybackState_parameters = new GetPlaybackStateRequest();
		GetPlaybackStateResponse _getPlaybackState__return = port
		        .getPlaybackState(_getPlaybackState_parameters);
		ContentPlaybackState content = _getPlaybackState__return.getContentPlaybackState();

		System.out.println("Show duration : " + content.getShowDuration());
		System.out.println("Show position : " + content.getShowPosition());
		System.out.println("Clip position : " + content.getClipDuration());
		System.out.println("Content id : " + content.getContentId());

		return content.getContentId();
	}

	public void testShow(String contentId) throws Fault {
		URL wsdlURL = ShowManagementV10.WSDL_LOCATION;
		ShowManagementV10 ss = new ShowManagementV10(wsdlURL, SHOW_SERVICE_NAME);
		ShowManagementServiceV10 port = ss.getShowManagementServiceV10();

		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		        String.format("http://%s:8080/dcinema/ws/smi/v1/ShowManagementService", host));

		GetSPLRequest parameters = new GetSPLRequest();
		parameters.setShowId(contentId);
		GetSPLResponse _getSPL_return = port.getSPL(parameters);
		System.out.println("Show : " + _getSPL_return.getShow());
	}

	public void testPlugin(String host, int port) {
		DolbyPlugin plugin = new DolbyPlugin();
		plugin.init(host, port, null, null);

		PluginStatus pluginStatus = plugin.getStatus();
		System.out.println(pluginStatus);

		UUID splId = pluginStatus.getPlId();
		SPLInfos splInfos = plugin.getSPL(splId);
		int seconds = 0;
		for (SPLElement element : splInfos.getElements()) {
			if (element.getName().contains("_FTR")) {
				break;
			}
			System.out.println(element.getName() + " " + element.getEditableUnitDuration() + " "
			        + element.getEditRate().getNumerator());
			int s = element.getEditableUnitDuration() / element.getEditRate().getNumerator();
			seconds += s;
			System.out.println(s);
		}
		System.out.println("total : " + seconds);
	}

	public static void main(String[] args) throws Fault {
		// String host = "10.40.241.18";
		String host = "10.10.241.3";
		int port = 8080;
		SampleClient sample = new SampleClient(host);

		String contentId = sample.testPlayback();
		sample.testShow(contentId);

		sample.testPlugin(host, port);
	}
}
