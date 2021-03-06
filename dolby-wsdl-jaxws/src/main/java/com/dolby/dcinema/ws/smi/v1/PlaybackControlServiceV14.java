package com.dolby.dcinema.ws.smi.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.1
 * 2015-07-09T17:38:11.721+02:00
 * Generated source version: 3.1.1
 * 
 */
@WebService(targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1", name = "PlaybackControlService_v1_4")
@XmlSeeAlso({com.dolby.dcinema.ws.smi.v1_4.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_3.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1.schemas.common.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_2.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_1.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1_0.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1.schemas.fault.ObjectFactory.class, com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface PlaybackControlServiceV14 {

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
     * @see portType PlaybackControlService_v1_3
     *       
     */
    @WebResult(name = "getPlaybackState2Response", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_3", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getPlaybackState2")
    public com.dolby.dcinema.ws.smi.v1_3.GetPlaybackState2Response getPlaybackState2(
        @WebParam(partName = "parameters", name = "getPlaybackState2Request", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_3")
        com.dolby.dcinema.ws.smi.v1_3.GetPlaybackState2Request parameters
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
     * @see portType PlaybackControlService_v1_2
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
     * getAutoInputSelect Operation
     *     Get the input selection mechanism that the decoder will automatically revert to upon playback completion.
     *     
     * 		  Parameters:
     *     None
     * 
     * 		  Response:
     * 			  autoInputSelectMode - Indicates what input selection mechanism that the decoder will automatically revert to upon playback completion.
     * 
     * 		  Related Services:
     * 		      autoInputSelect - Used to set the input selection mechanism that the decoder will automatically revert to upon playback completion.
     * 		     
     *       
     */
    @WebResult(name = "getAutoInputSelectResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getAutoInputSelect")
    public com.dolby.dcinema.ws.smi.v1_4.GetAutoInputSelectResponse getAutoInputSelect(
        @WebParam(partName = "parameters", name = "getAutoInputSelectRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4")
        com.dolby.dcinema.ws.smi.v1_4.GetAutoInputSelectRequest parameters
    ) throws Fault;

    /**
     * inputSelect Operation
     *    Sets the input selection mechanism the decoder is using (eg, hdmi, hd-sdi).
     * 
     * 		  Parameters:
     * 			  inputSelectMode - The new input selection mechanism that the decoder should use (eg, hdmi, hd-sdi).
     * 
     * 		  Response:
     *    None
     * 
     * 		  Related Services:
     * 		      getInputSelect - Used to get the input selection mechanism the decoder is using (eg, hdmi, hd-sdi).
     * 		      getInputContentProperties - Used to get the content properties that is being input into the decoder.
     *       
     */
    @WebResult(name = "inputSelectResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/inputSelect")
    public com.dolby.dcinema.ws.smi.v1_4.InputSelectResponse inputSelect(
        @WebParam(partName = "parameters", name = "inputSelectRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4")
        com.dolby.dcinema.ws.smi.v1_4.InputSelectRequest parameters
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
     * @see portType PlaybackControlService_v1_3
     *       
     */
    @WebResult(name = "setAutoPlaybackMode2Response", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_3", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/setAutoPlaybackMode2")
    public com.dolby.dcinema.ws.smi.v1_3.SetAutoPlaybackMode2Response setAutoPlaybackMode2(
        @WebParam(partName = "parameters", name = "setAutoPlaybackMode2Request", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_3")
        com.dolby.dcinema.ws.smi.v1_3.SetAutoPlaybackMode2Request parameters
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
     * @see portType PlaybackControlService_v1_3
     *       
     */
    @WebResult(name = "getAutoPlaybackMode2Response", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_3", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getAutoPlaybackMode2")
    public com.dolby.dcinema.ws.smi.v1_3.GetAutoPlaybackMode2Response getAutoPlaybackMode2(
        @WebParam(partName = "parameters", name = "getAutoPlaybackMode2Request", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_3")
        com.dolby.dcinema.ws.smi.v1_3.GetAutoPlaybackMode2Request parameters
    ) throws Fault;

    /**
     * autoInputSelect Operation
     *    Sets the auto input selection mechanism that the decoder will automatically revert to upon playback completion.
     * 
     * 		  Parameters:
     * 			  autoInputSelectMode - The new input selection mechanism that the decoder will automatically revert to upon playback completion.
     * 
     * 		  Response:
     *    None
     * 
     * 		  Related Services:
     * 		      getAutoInputSelect - Used to get the input selection mechanism that the decoder will automatically revert to upon playback completion.
     * 		      
     *       
     */
    @WebResult(name = "autoInputSelectResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/autoInputSelect")
    public com.dolby.dcinema.ws.smi.v1_4.AutoInputSelectResponse autoInputSelect(
        @WebParam(partName = "parameters", name = "autoInputSelectRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4")
        com.dolby.dcinema.ws.smi.v1_4.AutoInputSelectRequest parameters
    ) throws Fault;

    /**
     * getInputSelect Operation
     *     Get the input selection mechanism the decoder is using (eg, hdmi, hd-sdi).
     *     
     * 		  Parameters:
     *     None
     * 
     * 		  Response:
     * 			  inputSelectMode - Indicates what input selection mechanism the decoder is using (eg, hdmi, hd-sdi).
     * 
     * 		  Related Services:
     * 		      inputSelect - Used to set the input selection mechanism the decoder is using (eg, hdmi, hd-sdi).
     * 		      getInputContentProperties - Used to get the content properties that is being input into the decoder.
     *       
     */
    @WebResult(name = "getInputSelectResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getInputSelect")
    public com.dolby.dcinema.ws.smi.v1_4.GetInputSelectResponse getInputSelect(
        @WebParam(partName = "parameters", name = "getInputSelectRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4")
        com.dolby.dcinema.ws.smi.v1_4.GetInputSelectRequest parameters
    ) throws Fault;

    /**
     * getInputContentProperties Operation
     *     Get the content properties that is being input into the decoder.
     *     
     * 		  Parameters:
     *     None
     * 
     * 		  Response:
     * 			  contentProperties (many) - The properties of the content that is being input into the decoder.
     * 
     * 		  Related Services:
     *     None
     *       
     */
    @WebResult(name = "getInputContentPropertiesResponse", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/getInputContentProperties")
    public com.dolby.dcinema.ws.smi.v1_4.GetInputContentPropertiesResponse getInputContentProperties(
        @WebParam(partName = "parameters", name = "getInputContentPropertiesRequest", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_4")
        com.dolby.dcinema.ws.smi.v1_4.GetInputContentPropertiesRequest parameters
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
     * @see portType PlaybackControlService_v1_3
     *       
     */
    @WebResult(name = "setPlaybackMode2Response", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_3", partName = "result")
    @WebMethod(action = "http://www.dolby.com/dcinema/ws/smi/v1/setPlaybackMode2")
    public com.dolby.dcinema.ws.smi.v1_3.SetPlaybackMode2Response setPlaybackMode2(
        @WebParam(partName = "parameters", name = "setPlaybackMode2Request", targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1_3")
        com.dolby.dcinema.ws.smi.v1_3.SetPlaybackMode2Request parameters
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
}
