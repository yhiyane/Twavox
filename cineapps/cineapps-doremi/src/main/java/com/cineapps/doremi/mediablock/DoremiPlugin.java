package com.cineapps.doremi.mediablock;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.cineapps.doremi.service.DoremiService;
import com.cineapps.mediablock.core.dcinema.PluginStatus;
import com.cineapps.mediablock.core.dcinema.SPLInfos;
import com.cineapps.mediablock.core.logging.ErrorCommunicationLogger;
import com.cineapps.mediablock.plugin.MediablockPlugin;

public class DoremiPlugin implements MediablockPlugin {
	protected static final int WAIT_ELAPSE = 200;
	private static final Logger logger = ErrorCommunicationLogger.getLogger(DoremiPlugin.class);

	private DoremiService service = null;
	private String ipAddress;
	private int port = -1;
	private String login;
	private String password;

	public boolean init(String ipAddress, int port, String login, String password) {
		this.ipAddress = ipAddress;
		this.port = port;

		service = new DoremiService(ipAddress, port);
		if (service != null) {
			try {
				service.openConnexion();
			} catch (Exception ex) {
				logger.error(ex);
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean dispose() {
		if (service != null) {
			try {
				service.closeConnexion();
				service = null;
			} catch (Exception ex) {
				logger.error(ex);
				return false;
			}
			return true;
		}
		return false;
	}

	public PluginStatus getStatus() {
		PluginStatus status = new PluginStatus();
		// if (service != null) {
		// try {
		//
		// SPLStatus spl = dm.getStatusSPL();
		// switch (spl.getStatus()) {
		// case SPLStatus.STATUS_PLAY:
		// status.setStatus(PluginStatus.STATUS_START);
		// break;
		// case SPLStatus.STATUS_STOP:
		// status.setStatus(PluginStatus.STATUS_STOP);
		// break;
		// case SPLStatus.STATUS_PAUSE:
		// status.setStatus(PluginStatus.STATUS_PAUSE);
		// break;
		// case SPLStatus.STATUS_UNKNOW:
		// status.setStatus(PluginStatus.STATUS_NONE);
		// break;
		// }
		// status.setPlDuration(spl.getDuration());
		// status.setPlPosition(spl.getPosition());
		//
		// if (spl.getPlName() != null) {
		// status.setPlName(spl.getPlName());
		// } else {
		// status.setPlName("");
		// }
		//
		// if (spl.getId() == null) {
		// status.setPlUuidString("");
		// } else {
		// status.setPlId(UUIDUtils.fromByteArray(spl.getId()));
		// status.setPlUuidString(status.getPlId().toString());
		// }
		// status.setElDuration(spl.getElDuration());
		// status.setElPosition(spl.getElPosition());
		// status.setElId(UUIDUtils.fromByteArray(spl.getCpl()));
		//
		// if (status.getElId() == null) {
		// status.setElUuidString("");
		// } else {
		// status.setElUuidString(status.getElId().toString());
		// }
		//
		// // Add the mode of the schedule ( automatic or manual )
		// status.setManual(!dm.getSchedulerStatus());
		//
		// // remove the eventual errors
		// status.setError(PluginStatus.NO_ERROR);
		//
		// // Projector configuration:
		// // dm.getSNMP(".1.3.6.1.4.1.24391.1.6.1.3.1.0")
		// String resultSNMP = dm.getSNMP(".1.3.6.1.4.1.24391.1.6.1.3.1.0");
		// String[] results = resultSNMP.split("\n");
		// if (results.length > 1 && results[0].equalsIgnoreCase("integer")) {
		// if (Integer.valueOf(results[1]) != 1) {
		// status.setError(PluginStatus.ERROR_CONFIG_PROJECTOR);
		// } else {
		// // Projector communication:
		// // dm.getSNMP(".1.3.6.1.4.1.24391.1.6.1.2.1.0")
		// resultSNMP = dm.getSNMP(".1.3.6.1.4.1.24391.1.6.1.2.1.0");
		// results = resultSNMP.split("\n");
		// if (results.length > 1 && results[0].equalsIgnoreCase("integer")) {
		// if (Integer.valueOf(results[1]) != 1) {
		// status.setError(PluginStatus.ERROR_COMMUNICATION_PROJECTOR);
		// }
		// }
		// }
		// }
		// } catch (Exception ex) {
		// manageEx(ex);
		// // return an "error" status
		// status.setStatus(PluginStatus.STATUS_ERR_UKNOWN);
		// if (ex instanceof InternalException) {
		// status.setError(PluginStatus.ERROR_INTERNAL);
		// } else if (ex instanceof ProtocolException) {
		// status.setError(PluginStatus.ERROR_PROTOCOL);
		// } else if (ex instanceof ConnectionException) {
		// status.setError(PluginStatus.ERROR_NETWORK);
		// } else {
		// status.setError(PluginStatus.ERROR_NETWORK);
		// }
		// }
		// } else {
		// status.setStatus(PluginStatus.STATUS_ERR_UKNOWN);
		// status.setError(PluginStatus.ERROR_INTERNAL);
		// }
		return status;
	}

	public SPLInfos getSPL(UUID splId) {
		// try {
		// String xmlContent = dm.retrieveSPL(splId);
		// logger.debug(xmlContent);
		// SPLInfos spl = SPLParser.parse(xmlContent);
		// List<SPLElementInfos> splElementInfos = new
		// ArrayList<SPLElementInfos>();
		// for (CPLInfos cpl : spl.getCpls()) {
		// SPLElementInfos splElementToAdd = new SPLElementInfos();
		// splElementToAdd.setContent(retrieveCPLWithCache(cpl.getId()));
		// splElementInfos.add(splElementToAdd);
		// }
		// spl.setContentList(splElementInfos);
		// return spl;
		// } catch (Exception e) {
		// logger.error(e);
		// }
		return null;
	}
}