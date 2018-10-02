/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.dolby.server;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import com.cineapps.service.VideoStreamStatus;
import com.cineapps.service.VideoStreamWebService;
import com.dolby.dcinema.ws.smi.v1.Fault;
import com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV10;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ContentPlaybackState;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetScheduleModeRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetScheduleModeResponse;
import com.dolby.dcinema.ws.smi.v1_0.GoToPositionRequest;
import com.dolby.dcinema.ws.smi.v1_0.GoToPositionResponse;
import com.dolby.dcinema.ws.smi.v1_0.NextRequest;
import com.dolby.dcinema.ws.smi.v1_0.NextResponse;
import com.dolby.dcinema.ws.smi.v1_0.PauseRequest;
import com.dolby.dcinema.ws.smi.v1_0.PauseResponse;
import com.dolby.dcinema.ws.smi.v1_0.PlayRequest;
import com.dolby.dcinema.ws.smi.v1_0.PlayResponse;
import com.dolby.dcinema.ws.smi.v1_0.PreviousRequest;
import com.dolby.dcinema.ws.smi.v1_0.PreviousResponse;
import com.dolby.dcinema.ws.smi.v1_0.SelectContentRequest;
import com.dolby.dcinema.ws.smi.v1_0.SelectContentResponse;
import com.dolby.dcinema.ws.smi.v1_0.SetPlaybackModeRequest;
import com.dolby.dcinema.ws.smi.v1_0.SetPlaybackModeResponse;
import com.dolby.dcinema.ws.smi.v1_0.SetScheduleModeRequest;
import com.dolby.dcinema.ws.smi.v1_0.SetScheduleModeResponse;
import com.dolby.dcinema.ws.smi.v1_0.StopRequest;
import com.dolby.dcinema.ws.smi.v1_0.StopResponse;

@WebService(endpointInterface = "com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV10")
public class PlaybackControlServiceImpl implements PlaybackControlServiceV10 {

	private final String uuid = "urn:uuid:856f48d5-4d8f-40eb-973f-41c59194c6f3";
	private final VideoStreamWebService videoStreamWebService = new VideoStreamWebService();
	private final int MOVIE_DURATION_S = 2 * 60 * 60;

	public String getEndPointUrl(String ipAddress, int port) {
		return String.format("http://%s:%d/dcinema/ws/smi/v1/PlaybackControlService", ipAddress,
		        port);
	}

	public GetPlaybackStateResponse getPlaybackState(GetPlaybackStateRequest parameters)
	        throws Fault {
		ContentPlaybackState content = new ContentPlaybackState();
		try {
			Duration showDuration = DatatypeFactory.newInstance().newDuration(
			        MOVIE_DURATION_S * 1000);
			content.setShowDuration(showDuration);
			VideoStreamStatus status = videoStreamWebService.getStatus();
			int currentTime_s = 0;
			if (status != null) {
				currentTime_s = status.getCurrentTime_s();
			}
			Duration showPosition = DatatypeFactory.newInstance().newDuration(currentTime_s * 1000);
			content.setShowPosition(showPosition);
			content.setClipPosition(showPosition);
			content.setContentId(uuid);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		GetPlaybackStateResponse response = new GetPlaybackStateResponse();
		response.setContentPlaybackState(content);
		return response;
	}

	public SelectContentResponse selectContent(SelectContentRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayResponse play(PlayRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public StopResponse stop(StopRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public PauseResponse pause(PauseRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public NextResponse next(NextRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public PreviousResponse previous(PreviousRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public GetScheduleModeResponse getScheduleMode(GetScheduleModeRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public SetScheduleModeResponse setScheduleMode(SetScheduleModeRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public SetPlaybackModeResponse setPlaybackMode(SetPlaybackModeRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	public GoToPositionResponse goToPosition(GoToPositionRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

}
