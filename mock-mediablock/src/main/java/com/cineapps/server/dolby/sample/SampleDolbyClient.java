/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.dolby.sample;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

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

public class SampleDolbyClient {

	private final String host;
	private final int port;
	private static final QName PLAYBACK_SERVICE_NAME = new QName(
	        "http://www.dolby.com/dcinema/ws/smi/v1", "PlaybackControl_v1_0");
	private static final QName SHOW_SERVICE_NAME = new QName(
	        "http://www.dolby.com/dcinema/ws/smi/v1", "ShowManagement_v1_0");

	public SampleDolbyClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String testPlayback() throws Fault {
		URL wsdlURL = SampleDolbyClient.class.getResource("/dolby-smi/PlaybackControl.wsdl");
		PlaybackControlV10 ss = new PlaybackControlV10(wsdlURL, PLAYBACK_SERVICE_NAME);
		PlaybackControlServiceV10 entryPoint = ss.getPlaybackControlServiceV10();

		BindingProvider bp = (BindingProvider) entryPoint;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		        String.format("http://%s:%d/dcinema/ws/smi/v1/PlaybackControl_v1_0", host, port));

		GetPlaybackStateRequest _getPlaybackState_parameters = new GetPlaybackStateRequest();
		GetPlaybackStateResponse _getPlaybackState__return = entryPoint
		        .getPlaybackState(_getPlaybackState_parameters);
		ContentPlaybackState content = _getPlaybackState__return.getContentPlaybackState();

		System.out.println("Show duration : " + content.getShowDuration());
		System.out.println("Show position : " + content.getShowPosition());
		System.out.println("Clip position : " + content.getClipPosition());
		System.out.println("Content id : " + content.getContentId());

		return content.getContentId();
	}

	public void testShow(String contentId) throws Fault {
		URL wsdlURL = SampleDolbyClient.class.getResource("/dolby-smi/ShowManagement.wsdl");
		ShowManagementV10 ss = new ShowManagementV10(wsdlURL, SHOW_SERVICE_NAME);
		ShowManagementServiceV10 entryPoint = ss.getShowManagementServiceV10();

		BindingProvider bp = (BindingProvider) entryPoint;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		        String.format("http://%s:%d/dcinema/ws/smi/v1/ShowManagement_v1_0", host, port));

		GetSPLRequest parameters = new GetSPLRequest();
		parameters.setShowId(contentId);
		GetSPLResponse _getSPL_return = entryPoint.getSPL(parameters);
		System.out.println("Show : " + _getSPL_return.getShow());
	}

	public static void main(String[] args) throws Fault {
		// String host = "10.40.241.18";
		String host = "10.1.1.3";
		int port = 8080;
		SampleDolbyClient sample = new SampleDolbyClient(host, port);

		String contentId = sample.testPlayback();
		sample.testShow(contentId);
	}
}
