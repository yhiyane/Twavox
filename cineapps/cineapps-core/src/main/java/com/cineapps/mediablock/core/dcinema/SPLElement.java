/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.dcinema;

import java.util.UUID;

public class SPLElement {

	private UUID id;
	private String name;
	private Rate editRate;
	private int editableUnitDuration;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rate getEditRate() {
		return editRate;
	}

	public void setEditRate(Rate editRate) {
		this.editRate = editRate;
	}

	public int getEditableUnitDuration() {
		return editableUnitDuration;
	}

	public void setEditableUnitDuration(int editableUnitDuration) {
		this.editableUnitDuration = editableUnitDuration;
	}

	public int getDurationInMs() {
		return editableUnitDuration * editRate.getDenominator() / editRate.getNumerator() * 1000;
	}

}
