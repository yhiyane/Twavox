package com.dolby.dcinema.ws.smi.v1;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 3.1.1 2015-07-09T17:38:11.866+02:00
 * Generated source version: 3.1.1
 * 
 */
@WebServiceClient(name = "PlaybackControl_v1_4", wsdlLocation = "/datafs/config/dolby-smi/PlaybackControl.wsdl", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1")
public class PlaybackControlV14 extends Service {

	public final static URL WSDL_LOCATION;

	public final static QName SERVICE = new QName("http://www.dolby.com/dcinema/ws/smi/v1",
	        "PlaybackControl_v1_4");
	public final static QName PlaybackControlServiceV10 = new QName(
	        "http://www.dolby.com/dcinema/ws/smi/v1", "PlaybackControlService_v1_0");
	static {
		URL url = PlaybackControlV14.class.getResource("/dolby-smi/PlaybackControl.wsdl");
		if (url == null) {
			url = PlaybackControlV14.class.getClassLoader().getResource(
			        "/dolby-smi/PlaybackControl.wsdl");
		}
		if (url == null) {
			java.util.logging.Logger.getLogger(PlaybackControlV14.class.getName()).log(
			        java.util.logging.Level.INFO, "Can not initialize the default wsdl from {0}",
			        "/dolby-smi/PlaybackControl.wsdl");
		}
		WSDL_LOCATION = url;
	}

	public PlaybackControlV14(URL wsdlLocation) {
		super(wsdlLocation, SERVICE);
	}

	public PlaybackControlV14(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public PlaybackControlV14() {
		super(WSDL_LOCATION, SERVICE);
	}

	public PlaybackControlV14(WebServiceFeature... features) {
		super(WSDL_LOCATION, SERVICE, features);
	}

	public PlaybackControlV14(URL wsdlLocation, WebServiceFeature... features) {
		super(wsdlLocation, SERVICE, features);
	}

	public PlaybackControlV14(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
		super(wsdlLocation, serviceName, features);
	}

	/**
	 *
	 * @return returns PlaybackControlServiceV14
	 */
	@WebEndpoint(name = "PlaybackControlService_v1_0")
	public PlaybackControlServiceV14 getPlaybackControlServiceV10() {
		return super.getPort(PlaybackControlServiceV10, PlaybackControlServiceV14.class);
	}

	/**
	 * 
	 * @param features
	 *            A list of {@link javax.xml.ws.WebServiceFeature} to configure
	 *            on the proxy. Supported features not in the
	 *            <code>features</code> parameter will have their default
	 *            values.
	 * @return returns PlaybackControlServiceV14
	 */
	@WebEndpoint(name = "PlaybackControlService_v1_0")
	public PlaybackControlServiceV14 getPlaybackControlServiceV10(WebServiceFeature... features) {
		return super.getPort(PlaybackControlServiceV10, PlaybackControlServiceV14.class, features);
	}

}
