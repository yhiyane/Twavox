/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.job.multist;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.cineapps.job.multist.ws.MultiStModule;
import com.cineapps.model.property.SubtitleProperty;
import com.cineapps.service.ServicesHolder;
import com.cineapps.util.SchedulerUtils;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * This class implements a quartz job, used for the Multilingual Subtitle
 * option. This job is waiting for the notifications sent by the user
 * SmartPhone. The user has to use the CineApps application. The notifications
 * are sent when the user selects a subtitle language from the application. For
 * now, we only log the user selections to pay the cinema later. The format of
 * the log is defined by the SmartPhone application.
 * 
 */
public class MultiStWebServiceJob implements Job {

	private static final Logger logger = Logger.getLogger(MultiStWebServiceJob.class);
	private SubtitleProperty subtitleProperty;

	/**
	 * Starts now this job. The jobGroup is used to identify this job to cancel
	 * it after being scheduled.
	 * 
	 * @param jobGroup
	 */
	public static void scheduleNow(String jobGroup) {
		try {
			Scheduler scheduler = SchedulerUtils.getScheduler();
			JobDetail job = JobBuilder.newJob(MultiStWebServiceJob.class)
			        .withIdentity(UUID.randomUUID().toString(), jobGroup).build();
			Trigger trigger = TriggerBuilder.newTrigger().startNow().build();
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException e) {
			logger.error(e);
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			subtitleProperty = ServicesHolder.getInstance().getPropertyLoader()
			        .getSubtitleProperty();

			// run the web service with Guice
			Injector injector = Guice.createInjector(new MultiStModule());
			injector.getAllBindings();
			injector.createChildInjector().getAllBindings();

			Server server = new Server(subtitleProperty.getWebServicePort());
			ServletContextHandler servletHandler = new ServletContextHandler();
			servletHandler.addEventListener(injector
			        .getInstance(GuiceResteasyBootstrapServletContextListener.class));

			ServletHolder sh = new ServletHolder(HttpServletDispatcher.class);
			servletHandler.setInitParameter("resteasy.role.based.security", "true");
			servletHandler.addServlet(sh, "/*");

			server.setHandler(servletHandler);
			server.start();
			server.join();
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
