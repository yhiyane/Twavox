/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.mediablock;

import java.util.Arrays;
import java.util.List;

/**
 * Mapping between mediablock type and its implementation class name
 */
public enum MediablockType {

	DOLBY("DOLBY", "com.cineapps.dolby.mediablock.DolbyPlugin"), DOREMI("DOREMI",
	        "com.dvidea.videoproj.doremi.plugin.DoremiPlugin"), GDC("GDC",
	        "com.dvidea.videoproj.gdc.plugin.GdcPlugin"), QUBE("QUBE",
	        "com.dvidea.videoproj.qube.plugin.QubePlugin"), SONY("SONY",
	        "com.dvidea.videoproj.sony.plugin.SonyPlugin");

	private final String name;
	private final String clazz;
	private static List<MediablockType> types = Arrays.asList(DOLBY, DOREMI, GDC, QUBE, SONY);

	private MediablockType(String name, String clazz) {
		this.name = name;
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public String getClazz() {
		return clazz;
	}

	public static MediablockType fromName(String name) {
		for (MediablockType type : types) {
			if (type.getName().equals(name)) {
				return type;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return name;
	}

}
