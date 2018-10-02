/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.dcinema;

import java.util.List;
import java.util.UUID;

public class SPLInfos {

	private UUID uuid;
	private String title;
	private List<SPLElement> elements;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SPLElement> getElements() {
		return elements;
	}

	public void setElements(List<SPLElement> elements) {
		this.elements = elements;
	}

}
