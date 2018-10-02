/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.dolby.service;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.dolby.dcinema.ws.smi.v1.Fault;
import com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV10;
import com.dolby.dcinema.ws.smi.v1.PlaybackControlV10;
import com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV10;
import com.dolby.dcinema.ws.smi.v1.ShowManagementV10;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse;

public class DolbyService {

	private final PlaybackControlServiceV10 PLAYBACK_CONTROL_SERVICE;
	private final ShowManagementServiceV10 SHOW_MANAGEMENT_SERVICE;

	public DolbyService(String ipAddress, int port) {

		// Create Playback Control service
		QName PLAYBACK_SERVICE_NAME = new QName("http://www.dolby.com/dcinema/ws/smi/v1",
		        "PlaybackControl_v1_0");
		PlaybackControlV10 playbackControlV10 = new PlaybackControlV10(
		        PlaybackControlV10.WSDL_LOCATION, PLAYBACK_SERVICE_NAME);
		PLAYBACK_CONTROL_SERVICE = playbackControlV10.getPlaybackControlServiceV10();
		String playbackEndPoint = String.format(
		        "http://%s:%d/dcinema/ws/smi/v1/PlaybackControlService", ipAddress, port);
		// Change the end point
		((BindingProvider) PLAYBACK_CONTROL_SERVICE).getRequestContext().put(
		        BindingProvider.ENDPOINT_ADDRESS_PROPERTY, playbackEndPoint);

		// Create Show Management service
		QName SHOW_SERVICE_NAME = new QName("http://www.dolby.com/dcinema/ws/smi/v1",
		        "ShowManagement_v1_0");
		ShowManagementV10 showManagementV10 = new ShowManagementV10(
		        ShowManagementV10.WSDL_LOCATION, SHOW_SERVICE_NAME);
		SHOW_MANAGEMENT_SERVICE = showManagementV10.getShowManagementServiceV10();
		String showEndPoint = String.format("http://%s:%d/dcinema/ws/smi/v1/ShowManagementService",
		        ipAddress, port);
		// Change the end point
		((BindingProvider) SHOW_MANAGEMENT_SERVICE).getRequestContext().put(
		        BindingProvider.ENDPOINT_ADDRESS_PROPERTY, showEndPoint);
	}

	/* PlaybackControl */

	public GetPlaybackStateResponse getPlaybackState() throws Fault {
		GetPlaybackStateRequest _getPlaybackState_parameters = null;
		return PLAYBACK_CONTROL_SERVICE.getPlaybackState(_getPlaybackState_parameters);
	}

	/* ShowManagement */

	public GetSPLResponse getSPL(String dolbyShowUuid) throws RemoteException, Fault {
		GetSPLRequest request = new GetSPLRequest();
		request.setShowId(dolbyShowUuid);
		return SHOW_MANAGEMENT_SERVICE.getSPL(request);
	}

}
