/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.dolby.server;

import java.io.IOException;

import javax.jws.WebService;

import org.apache.commons.io.IOUtils;

import com.dolby.dcinema.ws.smi.v1.Fault;
import com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV10;
import com.dolby.dcinema.ws.smi.v1_0.CreateShowRequest;
import com.dolby.dcinema.ws.smi.v1_0.CreateShowResponse;
import com.dolby.dcinema.ws.smi.v1_0.CreateShowTimesRequest;
import com.dolby.dcinema.ws.smi.v1_0.CreateShowTimesResponse;
import com.dolby.dcinema.ws.smi.v1_0.DeleteShowRequest;
import com.dolby.dcinema.ws.smi.v1_0.DeleteShowResponse;
import com.dolby.dcinema.ws.smi.v1_0.DeleteShowTimesRequest;
import com.dolby.dcinema.ws.smi.v1_0.DeleteShowTimesResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetCueInfosRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetCueInfosResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetShowInfosRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetShowInfosResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetShowTimeInfosRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetShowTimeInfosResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetShowTimesRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetShowTimesResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetShowsRequest;
import com.dolby.dcinema.ws.smi.v1_0.GetShowsResponse;

@WebService(endpointInterface = "com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV10")
public class ShowManagementServiceImpl implements ShowManagementServiceV10 {

	private final String contentTitle;

	public ShowManagementServiceImpl(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getEndPointUrl(String ipAddress, int port) {
		return String.format("http://%s:%d/dcinema/ws/smi/v1/ShowManagementService", ipAddress,
		        port);
	}

	@Override
	public GetShowsResponse getShows(GetShowsRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetShowInfosResponse getShowInfos(GetShowInfosRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetSPLResponse getSPL(GetSPLRequest parameters) throws Fault {
		GetSPLResponse response = new GetSPLResponse();

		try {
			String show = String.format(
			        new String(IOUtils.toByteArray(ShowManagementServiceImpl.class
			                .getResourceAsStream("/dolby/showToReturn.xml"))), contentTitle);
			response.setShow(show);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public GetCueInfosResponse getCueInfos(GetCueInfosRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateShowResponse createShow(CreateShowRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteShowResponse deleteShow(DeleteShowRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetShowTimesResponse getShowTimes(GetShowTimesRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetShowTimeInfosResponse getShowTimeInfos(GetShowTimeInfosRequest parameters)
	        throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateShowTimesResponse createShowTimes(CreateShowTimesRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteShowTimesResponse deleteShowTimes(DeleteShowTimesRequest parameters) throws Fault {
		// TODO Auto-generated method stub
		return null;
	}

}
