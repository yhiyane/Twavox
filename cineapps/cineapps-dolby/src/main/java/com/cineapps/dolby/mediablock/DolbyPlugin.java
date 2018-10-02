/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.dolby.mediablock;

import java.rmi.RemoteException;

import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;

import com.cineapps.dolby.service.DolbyService;
import com.cineapps.dolby.utils.DolbyUtils;
import com.cineapps.mediablock.core.dcinema.PluginStatus;
import com.cineapps.mediablock.core.dcinema.SPLInfos;
import com.cineapps.mediablock.core.logging.ErrorCommunicationLogger;
import com.cineapps.mediablock.plugin.MediablockPlugin;
import com.dolby.dcinema.ws.smi.v1.Fault;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ContentPlaybackState;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse;
import com.sun.xml.messaging.saaj.util.JaxmURI.MalformedURIException;
import com.sun.xml.ws.client.ClientTransportException;

public class DolbyPlugin implements MediablockPlugin {

	private final Logger logger = ErrorCommunicationLogger.getLogger(DolbyPlugin.class);

	private DolbyService dolbyService;
	
	public DolbyPlugin(){
			logger.info("#I AM NUMBER G..............");
			System.out.println("#_NUMBER G..............");
		}

	public boolean init(String ipAddress, int port, String login, String password) {
		// Login and password are useless
		dolbyService = new DolbyService(ipAddress, port);
		// We check if the web service is available when we want to use it
		return true;
	}

	public boolean dispose() {
		// No disconnection to do
		return true;
	}

	public PluginStatus getStatus() {
		PluginStatus pluginStatus = new PluginStatus();
		pluginStatus.setError(PluginStatus.ERROR);
		try {
			// Consumes the service to get the playback state
			// and converts the informations to instantiate a PluginStatus
			// Sets the durations and the positions
			GetPlaybackStateResponse playbackState = dolbyService.getPlaybackState();
			if (playbackState != null) {
				ContentPlaybackState contentState = playbackState.getContentPlaybackState();
				pluginStatus.setShowDuration(DolbyUtils.durationToSeconds(contentState
				        .getShowDuration()));
				pluginStatus.setShowPosition(DolbyUtils.durationToSeconds(contentState
				        .getShowPosition()));
				pluginStatus.setElPosition(DolbyUtils.durationToSeconds(contentState
				        .getClipPosition()));
				pluginStatus.setPlId(DolbyUtils.toJavaUUID(contentState.getContentId()));

				// Sets the status
				if (pluginStatus.getShowPosition() != 0) {
					pluginStatus.setStatus(PluginStatus.STATUS_START);
				} else {
					pluginStatus.setStatus(PluginStatus.STATUS_STOP);
				}

				pluginStatus.setError(PluginStatus.OK);
				return pluginStatus;
			}
		} catch (ClientTransportException cte) {
			logger.error(cte);
		} catch (WebServiceException wse) {
			logger.error(wse);
		} catch (Fault e) {
			logger.error(e);
		}
		return pluginStatus;
	}

	public SPLInfos getSPL(java.util.UUID splId) {
		if (splId != null) {
			try {
				// Consumes the service
				GetSPLResponse splResponse = dolbyService.getSPL(DolbyUtils.toDolbyUUID(splId));
				return DolbyUtils.toSPLInfos(splResponse.getShow());

			} catch (MalformedURIException e) {
				logger.error(e);
			} catch (RemoteException e) {
				logger.error(e);
			} catch (Fault e) {
				logger.error(e);
			}
		}
		return null;
	}

}
