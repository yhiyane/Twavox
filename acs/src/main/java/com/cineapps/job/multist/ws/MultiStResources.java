/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.job.multist.ws;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ws/multist")
@Singleton
public class MultiStResources {

	@Inject
	MultiStWebService multiStWebService;

	@GET
	@Path("/current")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCurrentPlayedSubtitles() {
		return multiStWebService.getCurrentPlayedSubtitles();
	}
}
