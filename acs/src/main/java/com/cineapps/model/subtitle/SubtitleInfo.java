/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.subtitle;

import java.util.List;

/**
 * Object to store the informations for a subtitle.
 */
public class SubtitleInfo implements Comparable<SubtitleInfo> {

	private final String dcpTitle;
	private final String lang;
	private final int preMovieDurationMillis;
	private final List<SubtitleFile> subtitleFiles;
	private final long totalFilesLength;

	private int port;
	private boolean optional;

	public SubtitleInfo(String dcpTitle, List<SubtitleFile> subtitleFiles,
	        int preMovieDurationMillis, long totalFilesLength, String lang) {
		this.dcpTitle = dcpTitle;
		this.subtitleFiles = subtitleFiles;
		this.preMovieDurationMillis = preMovieDurationMillis;
		this.totalFilesLength = totalFilesLength;
		this.lang = lang;
	}

	public String getDcpTitle() {
		return dcpTitle;
	}

	public String getLang() {
		return lang;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public int getPreMovieDurationMillis() {
		return preMovieDurationMillis;
	}

	public List<SubtitleFile> getSubtitleFiles() {
		return subtitleFiles;
	}

	public long getTotalFilesLength() {
		return totalFilesLength;
	}

	@Override
	public int compareTo(SubtitleInfo o) {
		// the first is the longest
		if (totalFilesLength < o.getTotalFilesLength()) {
			return 1;
		}
		return -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dcpTitle == null) ? 0 : dcpTitle.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + (optional ? 1231 : 1237);
		result = prime * result + port;
		result = prime * result + preMovieDurationMillis;
		result = prime * result + ((subtitleFiles == null) ? 0 : subtitleFiles.hashCode());
		result = prime * result + (int) (totalFilesLength ^ (totalFilesLength >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubtitleInfo other = (SubtitleInfo) obj;
		if (dcpTitle == null) {
			if (other.dcpTitle != null)
				return false;
		} else if (!dcpTitle.equals(other.dcpTitle))
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (optional != other.optional)
			return false;
		if (port != other.port)
			return false;
		if (preMovieDurationMillis != other.preMovieDurationMillis)
			return false;
		if (subtitleFiles == null) {
			if (other.subtitleFiles != null)
				return false;
		} else if (!subtitleFiles.equals(other.subtitleFiles))
			return false;
		if (totalFilesLength != other.totalFilesLength)
			return false;
		return true;
	}

}
