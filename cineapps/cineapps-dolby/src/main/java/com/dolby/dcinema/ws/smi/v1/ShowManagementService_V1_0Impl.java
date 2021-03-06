
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.dolby.dcinema.ws.smi.v1;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.1
 * 2015-07-09T17:41:03.997+02:00
 * Generated source version: 3.1.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "ShowManagement_v1_1",
                      portName = "ShowManagementService_v1_0",
                      targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1",
                      wsdlLocation = "/dolby-smi/ShowManagement.wsdl",
                      endpointInterface = "com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11")
                      
public class ShowManagementService_V1_0Impl implements ShowManagementServiceV11 {

    private static final Logger LOG = Logger.getLogger(ShowManagementService_V1_0Impl.class.getName());

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#deleteShowTimes(com.dolby.dcinema.ws.smi.v1_0.DeleteShowTimesRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.DeleteShowTimesResponse deleteShowTimes(com.dolby.dcinema.ws.smi.v1_0.DeleteShowTimesRequest parameters) throws Fault    { 
        LOG.info("Executing operation deleteShowTimes");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.DeleteShowTimesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#getShowTimes(com.dolby.dcinema.ws.smi.v1_0.GetShowTimesRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GetShowTimesResponse getShowTimes(com.dolby.dcinema.ws.smi.v1_0.GetShowTimesRequest parameters) throws Fault    { 
        LOG.info("Executing operation getShowTimes");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GetShowTimesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#getShowInfos(com.dolby.dcinema.ws.smi.v1_0.GetShowInfosRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GetShowInfosResponse getShowInfos(com.dolby.dcinema.ws.smi.v1_0.GetShowInfosRequest parameters) throws Fault    { 
        LOG.info("Executing operation getShowInfos");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GetShowInfosResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#getShowTimeInfos(com.dolby.dcinema.ws.smi.v1_0.GetShowTimeInfosRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GetShowTimeInfosResponse getShowTimeInfos(com.dolby.dcinema.ws.smi.v1_0.GetShowTimeInfosRequest parameters) throws Fault    { 
        LOG.info("Executing operation getShowTimeInfos");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GetShowTimeInfosResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#createShowTimes(com.dolby.dcinema.ws.smi.v1_0.CreateShowTimesRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.CreateShowTimesResponse createShowTimes(com.dolby.dcinema.ws.smi.v1_0.CreateShowTimesRequest parameters) throws Fault    { 
        LOG.info("Executing operation createShowTimes");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.CreateShowTimesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#getShows(com.dolby.dcinema.ws.smi.v1_0.GetShowsRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GetShowsResponse getShows(com.dolby.dcinema.ws.smi.v1_0.GetShowsRequest parameters) throws Fault    { 
        LOG.info("Executing operation getShows");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GetShowsResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#getSPL2(com.dolby.dcinema.ws.smi.v1_1.GetSPL2Request  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_1.GetSPL2Response getSPL2(com.dolby.dcinema.ws.smi.v1_1.GetSPL2Request parameters) throws Fault    { 
        LOG.info("Executing operation getSPL2");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_1.GetSPL2Response _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#deleteShow(com.dolby.dcinema.ws.smi.v1_0.DeleteShowRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.DeleteShowResponse deleteShow(com.dolby.dcinema.ws.smi.v1_0.DeleteShowRequest parameters) throws Fault    { 
        LOG.info("Executing operation deleteShow");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.DeleteShowResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#createShow(com.dolby.dcinema.ws.smi.v1_0.CreateShowRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.CreateShowResponse createShow(com.dolby.dcinema.ws.smi.v1_0.CreateShowRequest parameters) throws Fault    { 
        LOG.info("Executing operation createShow");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.CreateShowResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#getSPL(com.dolby.dcinema.ws.smi.v1_0.GetSPLRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse getSPL(com.dolby.dcinema.ws.smi.v1_0.GetSPLRequest parameters) throws Fault    { 
        LOG.info("Executing operation getSPL");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.ShowManagementServiceV11#getCueInfos(com.dolby.dcinema.ws.smi.v1_0.GetCueInfosRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GetCueInfosResponse getCueInfos(com.dolby.dcinema.ws.smi.v1_0.GetCueInfosRequest parameters) throws Fault    { 
        LOG.info("Executing operation getCueInfos");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GetCueInfosResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

}
