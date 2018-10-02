/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.sony.server;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import com.cineapps.server.common.MediablockServer;
import com.cineapps.server.sony.api.SonyApiParam;
import com.cineapps.server.sony.module.SonyApiModule;
import com.cineapps.service.VideoStreamWebService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class SonyServer implements MediablockServer {

	private Logger logger = Logger.getLogger(SonyServer.class.getName());

	// User variables
	private final String title;
	private final String DCP = "_FTR_S_FR-XX_FR_51_2K_SPE_20090912_DUK_i3D-ngb";
	private UUID splUuid = UUID.fromString("c23ab440-0294-11e4-9191-0800200c9a66");
	private UUID cplUuid = UUID.fromString("1bf53165-3138-4848-9f35-8e7435772286");
	private final VideoStreamWebService videoStreamWebService = new VideoStreamWebService();
	private final int MOVIE_DURATION_S = 2 * 60 * 60;
	private final SonyApiParam sonyApiParam;
	private final String host;
	private final String keystorePath;

	public SonyServer(String host, String title, String keystorePath) {
		this.host = host;
		this.title = title;
		this.keystorePath = keystorePath;

		sonyApiParam = new SonyApiParam();
		sonyApiParam.setTitle(this.title + DCP);
		sonyApiParam.setCplUuid(cplUuid);
		sonyApiParam.setSplUuid(splUuid);
		sonyApiParam.setMovieDurationSeconds(MOVIE_DURATION_S);
		sonyApiParam.setVideoStreamWebService(videoStreamWebService);
	}

	// ##### SERVER SIDE #####

	@Override
	public void run(int port) {
		Injector injector = Guice.createInjector(new SonyApiModule(sonyApiParam));
		injector.getAllBindings();
		injector.createChildInjector().getAllBindings();

		Server server = new Server();

		ServletContextHandler servletHandler = new ServletContextHandler();
		servletHandler.addEventListener(injector
		        .getInstance(GuiceResteasyBootstrapServletContextListener.class));

		ServletHolder sh = new ServletHolder(HttpServletDispatcher.class);
		servletHandler.setInitParameter("resteasy.role.based.security", "true");
		servletHandler.addServlet(sh, "/*");
		server.setHandler(servletHandler);

		SslContextFactory contextFactory = new SslContextFactory();
		contextFactory.setKeyStorePath(keystorePath);
		contextFactory.setKeyStorePassword("password");
		SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(contextFactory,
		        org.eclipse.jetty.http.HttpVersion.HTTP_1_1.toString());

		HttpConfiguration config = new HttpConfiguration();
		config.setSecureScheme("https");
		config.setSecurePort(port);
		HttpConfiguration sslConfiguration = new HttpConfiguration(config);
		sslConfiguration.addCustomizer(new SecureRequestCustomizer());
		HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(sslConfiguration);

		ServerConnector connector = new ServerConnector(server, sslConnectionFactory,
		        httpConnectionFactory);
		connector.setHost(host);
		connector.setPort(port);
		server.addConnector(connector);

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
}
