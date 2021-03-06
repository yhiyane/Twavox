package com.dolby.dcinema.ws.smi.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.1
 * 2015-07-09T17:38:11.667+02:00
 * Generated source version: 3.1.1
 * 
 */
@WebService(targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1", name = "PlaybackControlService_v1_2")
@XmlSeeAlso({com.dolby.dcinema.ws.smi.v1_4.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_3.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1.schemas.common.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_2.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_1.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_0.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1.schemas.fault.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface PlaybackControlServiceV12 {

    /**
     * @see portType PlaybackControlService_v1_1
     *       
     */
    @WebResult(name = "setAutoPlaybackModeResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/setAutoPlaybackMode")
    public com.dolby.dcinema.ws.smi.v1_1.SetAutoPlaybackModeResponse setAutoPlaybackMode(
        @WebParam(partName = "parameters", name = "setAutoPlaybackModeRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1")
        com.dolby.dcinema.ws.smi.v1_1.SetAutoPlaybackModeRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "playResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/play")
    public com.dolby.dcinema.ws.smi.v1_0.PlayResponse play(
        @WebParam(partName = "parameters", name = "playRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.PlayRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_1
     *       
     */
    @WebResult(name = "fireCueResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/fireCue")
    public com.dolby.dcinema.ws.smi.v1_1.FireCueResponse fireCue(
        @WebParam(partName = "parameters", name = "fireCueRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1")
        com.dolby.dcinema.ws.smi.v1_1.FireCueRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "goToPositionResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/goToPosition")
    public com.dolby.dcinema.ws.smi.v1_0.GoToPositionResponse goToPosition(
        @WebParam(partName = "parameters", name = "goToPositionRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GoToPositionRequest parameters
    ) throws Fault;

    /**
     * getPrepSuiteState Operation
     *     Indicates the last prep suite state.
     *     
     * 		  Parameters:
     * 		      None
     * 
     * 		  Response:
     *     prepSuiteStatus - The status of the last prep suite
     *     clipId - The UUID of the clip which corresponds to last prep suite. (optional)
     *     
     * Exceptions:
     *     NotConnected - If the ContentPlayer or Decoder component is not connected
     * 
     * 		  Related Services:
     * 		      getPlaybackState - The current transport state.  The transport will transition 
     * 		      to TransportStateTypeEnum#UNSELECTED if a prep suite failure occurs.
     *       
     */
    @WebResult(name = "getPrepSuiteStateResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_2", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getPrepSuiteState")
    public com.dolby.dcinema.ws.smi.v1_2.GetPrepSuiteStateResponse getPrepSuiteState(
        @WebParam(partName = "parameters", name = "getPrepSuiteStateRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_2")
        com.dolby.dcinema.ws.smi.v1_2.GetPrepSuiteStateRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "getScheduleModeResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getScheduleMode")
    public com.dolby.dcinema.ws.smi.v1_0.GetScheduleModeResponse getScheduleMode(
        @WebParam(partName = "parameters", name = "getScheduleModeRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GetScheduleModeRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_1
     *       
     */
    @WebResult(name = "getAutoPlaybackModeResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getAutoPlaybackMode")
    public com.dolby.dcinema.ws.smi.v1_1.GetAutoPlaybackModeResponse getAutoPlaybackMode(
        @WebParam(partName = "parameters", name = "getAutoPlaybackModeRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1")
        com.dolby.dcinema.ws.smi.v1_1.GetAutoPlaybackModeRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "stopResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/stop")
    public com.dolby.dcinema.ws.smi.v1_0.StopResponse stop(
        @WebParam(partName = "parameters", name = "stopRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.StopRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_1
     *       
     */
    @WebResult(name = "getPurgeSuiteStateResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getPurgeSuiteState")
    public com.dolby.dcinema.ws.smi.v1_1.GetPurgeSuiteStateResponse getPurgeSuiteState(
        @WebParam(partName = "parameters", name = "getPurgeSuiteStateRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_1")
        com.dolby.dcinema.ws.smi.v1_1.GetPurgeSuiteStateRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "getPlaybackStateResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getPlaybackState")
    public com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse getPlaybackState(
        @WebParam(partName = "parameters", name = "getPlaybackStateRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "setScheduleModeResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/setScheduleMode")
    public com.dolby.dcinema.ws.smi.v1_0.SetScheduleModeResponse setScheduleMode(
        @WebParam(partName = "parameters", name = "setScheduleModeRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.SetScheduleModeRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "nextResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/next")
    public com.dolby.dcinema.ws.smi.v1_0.NextResponse next(
        @WebParam(partName = "parameters", name = "nextRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.NextRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "setPlaybackModeResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/setPlaybackMode")
    public com.dolby.dcinema.ws.smi.v1_0.SetPlaybackModeResponse setPlaybackMode(
        @WebParam(partName = "parameters", name = "setPlaybackModeRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.SetPlaybackModeRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "previousResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/previous")
    public com.dolby.dcinema.ws.smi.v1_0.PreviousResponse previous(
        @WebParam(partName = "parameters", name = "previousRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.PreviousRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "selectContentResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/selectContent")
    public com.dolby.dcinema.ws.smi.v1_0.SelectContentResponse selectContent(
        @WebParam(partName = "parameters", name = "selectContentRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.SelectContentRequest parameters
    ) throws Fault;

    /**
     * @see portType PlaybackControlService_v1_0
     *       
     */
    @WebResult(name = "pauseResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/pause")
    public com.dolby.dcinema.ws.smi.v1_0.PauseResponse pause(
        @WebParam(partName = "parameters", name = "pauseRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_0")
        com.dolby.dcinema.ws.smi.v1_0.PauseRequest parameters
    ) throws Fault;
}
