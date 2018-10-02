import javax.inject.Singleton;

import jaxrs.ClientErrorExceptionMapper;
import jaxrs.GsonMessageBodyHandler;
import jaxrs.HelloResource;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.guice.RequestScoped;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import service.HelloWorld;
import service.HelloWorldFI;
import service.HelloWorldPL;
import service.User;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

public class Main {

	private static void runHttp(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new HelloModule(args));

		injector.getAllBindings();

		injector.createChildInjector().getAllBindings();

		Server server = new Server(8080);
		ServletContextHandler servletHandler = new ServletContextHandler();
		servletHandler.addEventListener(injector
		        .getInstance(GuiceResteasyBootstrapServletContextListener.class));

		ServletHolder sh = new ServletHolder(HttpServletDispatcher.class);
		servletHandler.setInitParameter("resteasy.role.based.security", "true");
		servletHandler.addFilter(new FilterHolder(injector.getInstance(HelloFilter.class)), "/*",
		        null);
		servletHandler.addServlet(sh, "/*");

		server.setHandler(servletHandler);
		server.start();
		server.join();
	}

	private static void runHttps(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new HelloModule(args));
		injector.getAllBindings();
		injector.createChildInjector().getAllBindings();

		Server server = new Server();

		ServletContextHandler servletHandler = new ServletContextHandler();
		servletHandler.addEventListener(injector
		        .getInstance(GuiceResteasyBootstrapServletContextListener.class));

		ServletHolder sh = new ServletHolder(HttpServletDispatcher.class);
		servletHandler.setInitParameter("resteasy.role.based.security", "true");
		servletHandler.addFilter(new FilterHolder(injector.getInstance(HelloFilter.class)), "/*",
		        null);
		servletHandler.addServlet(sh, "/*");
		server.setHandler(servletHandler);

		SslContextFactory contextFactory = new SslContextFactory();
		contextFactory.setKeyStorePath("src/main/resources/keystore");
		contextFactory.setKeyStorePassword("password");
		SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(contextFactory,
		        org.eclipse.jetty.http.HttpVersion.HTTP_1_1.toString());

		HttpConfiguration config = new HttpConfiguration();
		config.setSecureScheme("https");
		config.setSecurePort(8080);
		config.setOutputBufferSize(32786);
		config.setRequestHeaderSize(8192);
		config.setResponseHeaderSize(8192);
		HttpConfiguration sslConfiguration = new HttpConfiguration(config);
		sslConfiguration.addCustomizer(new SecureRequestCustomizer());
		HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(sslConfiguration);

		ServerConnector connector = new ServerConnector(server, sslConnectionFactory,
		        httpConnectionFactory);
		connector.setPort(8080);
		server.addConnector(connector);

		server.start();
		server.join();
	}

	public static void main(String[] args) throws Exception {
		runHttps(args);
	}

	private static class HelloModule extends RequestScopeModule {

		private String[] args;

		public HelloModule(String[] args) {
			this.args = args;
		}

		@Provides
		@Singleton
		public HelloWorld provideHelloWorld() {
			if (args.length > 0 && args[0].equals("fi")) {
				return new HelloWorldFI();
			} else {
				return new HelloWorldPL();
			}
		}

		@Override
		protected void configure() {
			super.configure();
			bind(GsonMessageBodyHandler.class);
			bind(HelloResource.class);
			bind(ClientErrorExceptionMapper.class);
		}

		@Provides
		@RequestScoped
		public User provideUser() {
			return ResteasyProviderFactory.getContextData(User.class);
		}
	}
}
