/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.sony.module;

import javax.inject.Singleton;

import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;

import com.cineapps.server.sony.api.SonyApi;
import com.cineapps.server.sony.api.SonyApiImpl;
import com.cineapps.server.sony.api.SonyApiParam;
import com.cineapps.server.sony.resources.ClientErrorExceptionMapper;
import com.cineapps.server.sony.resources.GsonMessageBodyHandler;
import com.cineapps.server.sony.resources.SonyResources;
import com.google.inject.Provides;

public class SonyApiModule extends RequestScopeModule {

	private final SonyApiParam sonyApiParam;

	public SonyApiModule(SonyApiParam sonyApiParam) {
		this.sonyApiParam = sonyApiParam;
	}

	@Provides
	@Singleton
	public SonyApi provideApi() {
		return new SonyApiImpl(sonyApiParam);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(GsonMessageBodyHandler.class);
		bind(SonyResources.class);
		bind(ClientErrorExceptionMapper.class);
	}
}
