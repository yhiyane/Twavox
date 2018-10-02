/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model;

public enum Action {

	READ("read"), GENERATE("generate"), AUTOGEN("auto-gen"), AUTOREAD("auto-read");

	private final String action;

	private Action(String action) {
		this.action = action;
	}

	private String getAction() {
		return action;
	}

	public static Action fromString(String actionStr) {
		for (Action action : Action.values()) {
			if (action.getAction().equals(actionStr)) {
				return action;
			}
		}
		return null;
	}
}
