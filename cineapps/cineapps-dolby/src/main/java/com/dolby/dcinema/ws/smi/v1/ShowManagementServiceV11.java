package com.dolby.dcinema.ws.smi.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.1
 * 2015-07-09T17:41:04.065+02:00
 * Generated source version: 3.1.1
 * 
 */
@WebService(targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1", name = "ShowManagementService_v1_1")
@XmlSeeAlso({com.dolby.dcinema.ws.smi.v1.schemas.showmanagement.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1.schemas.common.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_1.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_0.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1.schemas.fault.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ShowManagementServiceV11 {

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "deleteShowTimesResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/deleteShowTimes")
    public com.dolby.dcinema.ws.smi.v1_0.DeleteShowTimesResponse deleteShowTimes(
        @WebParam(partName = "parameters", name = "deleteShowTimesRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.DeleteShowTimesRequest parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "getShowTimesResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getShowTimes")
    public com.dolby.dcinema.ws.smi.v1_0.GetShowTimesResponse getShowTimes(
        @WebParam(partName = "parameters", name = "getShowTimesRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GetShowTimesRequest parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "getShowInfosResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getShowInfos")
    public com.dolby.dcinema.ws.smi.v1_0.GetShowInfosResponse getShowInfos(
        @WebParam(partName = "parameters", name = "getShowInfosRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GetShowInfosRequest parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "getShowTimeInfosResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getShowTimeInfos")
    public com.dolby.dcinema.ws.smi.v1_0.GetShowTimeInfosResponse getShowTimeInfos(
        @WebParam(partName = "parameters", name = "getShowTimeInfosRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GetShowTimeInfosRequest parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "createShowTimesResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/createShowTimes")
    public com.dolby.dcinema.ws.smi.v1_0.CreateShowTimesResponse createShowTimes(
        @WebParam(partName = "parameters", name = "createShowTimesRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.CreateShowTimesRequest parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "getShowsResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getShows")
    public com.dolby.dcinema.ws.smi.v1_0.GetShowsResponse getShows(
        @WebParam(partName = "parameters", name = "getShowsRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GetShowsRequest parameters
    ) throws Fault;

    /**
     * getSPL2 Operation
     *     Gets the show play list (SPL) using the Dolby defined XML format.
     *     The SPL includes the parameterized cue value elements with target
     *     namespace 'http://www.dolby.com/dcinema/ws/smi/v11/SPL'
     * 
     * 		  Parameters:
     * 		      showId - The unique identifier of the show whose show play list is being retrieved.
     * 
     * 		  Response:
     *     show - A string representing the XML show play list.
     * 
     * 		  Exceptions:
     * 		      NotFound - If no show with the matching uuid could be found.
     * 		      
     * 		  Related Services:
     * 		      getShows - This service can be used to easily detect what shows exist within the system.
     *         
     */
    @WebResult(name = "getSPL2Response", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getSPL2")
    public com.dolby.dcinema.ws.smi.v1_1.GetSPL2Response getSPL2(
        @WebParam(partName = "parameters", name = "getSPL2Request", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1")
        com.dolby.dcinema.ws.smi.v1_1.GetSPL2Request parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "deleteShowResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/deleteShow")
    public com.dolby.dcinema.ws.smi.v1_0.DeleteShowResponse deleteShow(
        @WebParam(partName = "parameters", name = "deleteShowRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.DeleteShowRequest parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "createShowResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/createShow")
    public com.dolby.dcinema.ws.smi.v1_0.CreateShowResponse createShow(
        @WebParam(partName = "parameters", name = "createShowRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.CreateShowRequest parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "getSPLResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getSPL")
    public com.dolby.dcinema.ws.smi.v1_0.GetSPLResponse getSPL(
        @WebParam(partName = "parameters", name = "getSPLRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GetSPLRequest parameters
    ) throws Fault;

    /**
     * @see portType ShowManagementService_v1_0
     *         
     */
    @WebResult(name = "getCueInfosResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getCueInfos")
    public com.dolby.dcinema.ws.smi.v1_0.GetCueInfosResponse getCueInfos(
        @WebParam(partName = "parameters", name = "getCueInfosRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GetCueInfosRequest parameters
    ) throws Fault;
}
