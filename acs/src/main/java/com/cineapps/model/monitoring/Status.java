/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.monitoring;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.cineapps.util.DcpNameUtils;

/**
 * The global status of the Twavox
 */
public class Status {

	private static final Logger logger = Logger.getLogger(Status.class);
	// Summary
	private String state;
	private String message;
	private String dcp;

	// Indicators
	private boolean isWifiReachable = true;
	private Boolean isPlayingVi = null; // Auto-calculated in updateIndicators()
	                                    // method
	private int nbStBasic; // Auto-calculated in updateIndicators() method
	private int nbStOcap; // Auto-calculated in updateIndicators() method

	// Detailed parameters
	private String detail;
	private Map<String, String> subtitles;
	private String diskPercent;
	private List<String> folders;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDcp() {
		return dcp;
	}

	public void setDcp(String dcp) {
		this.dcp = dcp;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Map<String, String> getSubtitles() {
		return subtitles;
	}

	public void setSubtitles(Map<String, String> subtitles) {
		this.subtitles = subtitles;
	}

	public String getDiskPercent() {
		return diskPercent;
	}

	public void setDiskPercent(String diskPercent) {
		this.diskPercent = diskPercent;
	}

	public List<String> getFolders() {
		return folders;
	}

	public void setFolders(List<String> folders) {
		this.folders = folders;
	}

	public Boolean isWifiReachable() {
		return isWifiReachable;
	}

	public void setWifiReachable(Boolean isWifiReachable) {
		this.isWifiReachable = isWifiReachable;
	}

	public Boolean isPlayingVi() {
		return isPlayingVi;
	}

	public void setPlayingVi(Boolean isPlayingVi) {
		this.isPlayingVi = isPlayingVi;
	}

	public int getNbStBasic() {
		return nbStBasic;
	}

	public void setNbStBasic(int nbStBasic) {
		this.nbStBasic = nbStBasic;
	}

	public int getNbStOcap() {
		return nbStOcap;
	}

	public void setNbStOcap(int nbStOcap) {
		this.nbStOcap = nbStOcap;
	}

	/**
	 * Updates the auto-calculated attributes.
	 */
	public void updateIndicators() {
		// Update isPlayingVi attribute
		this.isPlayingVi = DcpNameUtils.isVIVersion(dcp);

		// Update nbStBasic and nbStOcap attributes
		this.nbStBasic = 0;
		this.nbStOcap = 0;
		if (this.subtitles != null) {
			for (String subtitle : subtitles.values()) {
				if (DcpNameUtils.isForHearingImpaired(subtitle)) {
					this.nbStOcap++;
				} else {
					this.nbStBasic++;
				}
			}
		}
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String string;
		try {
			string = mapper.writeValueAsString(this);
		} catch (IOException e) {
			logger.error(e);
			string = "{\"message\":\"Error\",\"error\":\"Cannot map status instance\"}";
		}
		return string;
	}
}
