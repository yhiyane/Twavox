/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.job.multist.ws;

import javax.inject.Singleton;

import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;

import com.google.inject.Provides;

public class MultiStModule extends RequestScopeModule {

	@Provides
	@Singleton
	public MultiStWebService provideApi() {
		return new MultiStWebServiceImpl();
	}

	@Override
	protected void configure() {
		super.configure();
		bind(MultiStResources.class);
	}
}
