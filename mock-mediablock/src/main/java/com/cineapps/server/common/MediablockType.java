/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.common;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public enum MediablockType {

	DOLBY("DOLBY"), DOREMI("DOREMI"), GDC("GDC"), QUBE("QUBE"), SONY("SONY");

	private final String name;
	private static List<MediablockType> types = Arrays.asList(DOLBY, DOREMI, GDC, QUBE, SONY);

	private MediablockType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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

	public static void main(String[] args) throws URISyntaxException {
		System.out.println(MediablockType.class.getResource("/dolby-smi/PlaybackControl.wsdl"));
	}
}
