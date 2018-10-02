/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.plugin;

import java.util.UUID;

import com.cineapps.mediablock.core.dcinema.PluginStatus;
import com.cineapps.mediablock.core.dcinema.SPLInfos;

public interface MediablockPlugin {

	/**
	 * Initializes the connection to the Mediablock. Returns true if the
	 * connection succeeded, false otherwise.
	 * 
	 * @param ipAddress
	 * @param port
	 * @param login
	 * @param password
	 * @return
	 */
	public boolean init(String ipAddress, int port, String login, String password);

	/**
	 * Disconnects to the Mediablock. Returns true if the disconnection
	 * succeeded, false otherwise.
	 * 
	 * @return
	 */
	public boolean dispose();

	/**
	 * Returns the status of the Mediablock.
	 * 
	 * @return
	 */
	public PluginStatus getStatus();

	/**
	 * Returns the informations about the playlist identified by its uuid.
	 * 
	 * @param splId
	 * @return
	 */
	public SPLInfos getSPL(UUID splUuid);
}
