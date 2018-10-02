/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.dolby.mediablock;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;

import com.cineapps.dolby.service.DolbyService;
import com.cineapps.dolby.utils.DolbyUtils;
import com.dolby.dcinema.ws.smi.v1.Fault;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ContentPlaybackState;
import com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse;
import com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse;
import com.dvidea.core.mediablock.logs.LogReport;
import com.dvidea.core.mediablock.plugin.CPLConsistency;
import com.dvidea.core.mediablock.plugin.CPLInfos;
import com.dvidea.core.mediablock.plugin.FullSPLConsistency;
import com.dvidea.core.mediablock.plugin.IPlugin;
import com.dvidea.core.mediablock.plugin.IngestStatus;
import com.dvidea.core.mediablock.plugin.KDMInfos;
import com.dvidea.core.mediablock.plugin.MacroInfos;
import com.dvidea.core.mediablock.plugin.MediablockInfos;
import com.dvidea.core.mediablock.plugin.PluginStatus;
import com.dvidea.core.mediablock.plugin.SPLConsistency;
import com.dvidea.core.mediablock.plugin.ScheduleInfos;
import com.dvidea.core.mediablock.plugin.TriggerInfos;

public class DolbyPlugin implements IPlugin {

	private final Logger logger = Logger.getLogger(DolbyPlugin.class);

	private DolbyService dolbyService;
	
	public DolbyPlugin(){
			logger.info("#I AM NUMBER E..............");
			System.out.println("#_NUMBER E*..............");
		}

	public boolean dispose() {
		// No disconnection to do
		return true;
	}

	public boolean init(String ipAddress, int port, String arg2, String arg3, String... arg4) {
		// Login and password are useless
		dolbyService = new DolbyService(ipAddress, port);
		// We check if the web service is available when we want to use it
		return true;
	}

	public com.dvidea.core.mediablock.plugin.PluginStatus getStatus() {
		PluginStatus pluginStatus = new PluginStatus();
		pluginStatus.setError(PluginStatus.ERROR_INTERNAL);
		try {
			// Consumes the service to get the playback state
			// and converts the informations to instantiate a PluginStatus
			// Sets the durations and the positions
			GetPlaybackStateResponse playbackState = dolbyService.getPlaybackState();
			if (playbackState != null) {
				ContentPlaybackState contentState = playbackState.getContentPlaybackState();
				pluginStatus.setShowDuration(DolbyUtils.durationToSeconds(contentState
				        .getShowDuration() != null ? contentState.getShowDuration() : contentState
				        .getClipDuration()));
				pluginStatus.setShowPosition(DolbyUtils.durationToSeconds(contentState
				        .getShowPosition() != null ? contentState.getShowPosition() : contentState
				        .getClipPosition()));
				pluginStatus.setElPosition(DolbyUtils.durationToSeconds(contentState
				        .getClipPosition()));
				pluginStatus.setPlId(DolbyUtils.toJavaUUID(contentState.getContentId()));

				// Sets the status
				if (pluginStatus.getShowPosition() != 0) {
					pluginStatus.setStatus(PluginStatus.STATUS_START);
				} else {
					pluginStatus.setStatus(PluginStatus.STATUS_STOP);
				}

				pluginStatus.setError(PluginStatus.NO_ERROR);
				return pluginStatus;
			}
		} catch (WebServiceException wse) {
			logger.error(wse);
		} catch (Fault e) {
			logger.error(e);
		}
		return pluginStatus;
	}

	public com.dvidea.core.mediablock.plugin.SPLInfos getSPL(UUID splId) {
		if (splId != null) {
			try {
				// Consumes the service
				GetSPLResponse splResponse = dolbyService.getSPL(DolbyUtils.toDolbyUUID(splId));
				return DolbyUtils.toSPLInfos(splResponse.getShow());

			} catch (RemoteException e) {
				logger.error(e);
			} catch (Fault e) {
				logger.error(e);
			}
		}
		return null;
	}

	public CPLConsistency checkCPLConsistency(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public SPLConsistency checkSPLConsistency(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public FullSPLConsistency checkSPLConsistency(com.dvidea.core.mediablock.plugin.SPLInfos arg0,
	        Date arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteCPL(UUID arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteKDM(UUID arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteSPL(UUID arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteSchedule(Date arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean executeMacro(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public URL getAssetURL(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public CPLInfos getCPL(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CPLInfos> getCPLs() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getDiskAvailable() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getDiskSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

	public MediablockInfos getInfos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IngestStatus> getIngestStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public IngestStatus getIngestStatus(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<KDMInfos> getKdms() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MacroInfos> getMacros() {
		// TODO Auto-generated method stub
		return null;
	}

	public LogReport getSMLog(Date arg0, Date arg1, UUID arg2, UUID arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	public LogReport getSMLogByCplId(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public LogReport getSMLogByDate(Date arg0, Date arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public LogReport getSMLogByKdmId(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<com.dvidea.core.mediablock.plugin.SPLInfos> getSPLs() {
		// TODO Auto-generated method stub
		return null;
	}

	public ScheduleInfos getSchedule(Date arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ScheduleInfos> getSchedule(Date arg0, Date arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TriggerInfos> getTriggers() {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] getXMLSMLog(Date arg0, Date arg1, UUID arg2, UUID arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] getXMLSMLogByCplId(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] getXMLSMLogByDate(Date arg0, Date arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] getXMLSMLogByKdmId(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean ingestCancel(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean ingestKDM(URL arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String ingestPackage(URL arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean jumpInElement(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void jumpToInPlaylist(int arg0) {
		// TODO Auto-generated method stub

	}

	public boolean load(com.dvidea.core.mediablock.plugin.SPLInfos arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean next() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean pause() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean pauseTransfer(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean previous() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean resumeTransfer(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveSchedule(ScheduleInfos arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setManualMode(boolean arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean start() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean storeSPL(com.dvidea.core.mediablock.plugin.SPLInfos arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean switchManualMode() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean unpause() {
		// TODO Auto-generated method stub
		return false;
	}

}
