
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
 * 2015-07-09T17:38:11.488+02:00
 * Generated source version: 3.1.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "PlaybackControl_v1_4",
                      portName = "PlaybackControlService_v1_0",
                      targetNamespace = "http://www.dolby.com/dcinema/ws/smi/v1",
                      wsdlLocation = "/dolby-smi/PlaybackControl.wsdl",
                      endpointInterface = "com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14")
                      
public class PlaybackControlService_V1_0Impl3 implements PlaybackControlServiceV14 {

    private static final Logger LOG = Logger.getLogger(PlaybackControlService_V1_0Impl3.class.getName());

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#setAutoPlaybackMode(com.dolby.dcinema.ws.smi.v1_1.SetAutoPlaybackModeRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_1.SetAutoPlaybackModeResponse setAutoPlaybackMode(com.dolby.dcinema.ws.smi.v1_1.SetAutoPlaybackModeRequest parameters) throws Fault    { 
        LOG.info("Executing operation setAutoPlaybackMode");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_1.SetAutoPlaybackModeResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#play(com.dolby.dcinema.ws.smi.v1_0.PlayRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.PlayResponse play(com.dolby.dcinema.ws.smi.v1_0.PlayRequest parameters) throws Fault    { 
        LOG.info("Executing operation play");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.PlayResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getPlaybackState2(com.dolby.dcinema.ws.smi.v1_3.GetPlaybackState2Request  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_3.GetPlaybackState2Response getPlaybackState2(com.dolby.dcinema.ws.smi.v1_3.GetPlaybackState2Request parameters) throws Fault    { 
        LOG.info("Executing operation getPlaybackState2");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_3.GetPlaybackState2Response _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#goToPosition(com.dolby.dcinema.ws.smi.v1_0.GoToPositionRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GoToPositionResponse goToPosition(com.dolby.dcinema.ws.smi.v1_0.GoToPositionRequest parameters) throws Fault    { 
        LOG.info("Executing operation goToPosition");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GoToPositionResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getPrepSuiteState(com.dolby.dcinema.ws.smi.v1_2.GetPrepSuiteStateRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_2.GetPrepSuiteStateResponse getPrepSuiteState(com.dolby.dcinema.ws.smi.v1_2.GetPrepSuiteStateRequest parameters) throws Fault    { 
        LOG.info("Executing operation getPrepSuiteState");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_2.GetPrepSuiteStateResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getScheduleMode(com.dolby.dcinema.ws.smi.v1_0.GetScheduleModeRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GetScheduleModeResponse getScheduleMode(com.dolby.dcinema.ws.smi.v1_0.GetScheduleModeRequest parameters) throws Fault    { 
        LOG.info("Executing operation getScheduleMode");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GetScheduleModeResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getAutoInputSelect(com.dolby.dcinema.ws.smi.v1_4.GetAutoInputSelectRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_4.GetAutoInputSelectResponse getAutoInputSelect(com.dolby.dcinema.ws.smi.v1_4.GetAutoInputSelectRequest parameters) throws Fault    { 
        LOG.info("Executing operation getAutoInputSelect");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_4.GetAutoInputSelectResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#inputSelect(com.dolby.dcinema.ws.smi.v1_4.InputSelectRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_4.InputSelectResponse inputSelect(com.dolby.dcinema.ws.smi.v1_4.InputSelectRequest parameters) throws Fault    { 
        LOG.info("Executing operation inputSelect");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_4.InputSelectResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#fireCue(com.dolby.dcinema.ws.smi.v1_1.FireCueRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_1.FireCueResponse fireCue(com.dolby.dcinema.ws.smi.v1_1.FireCueRequest parameters) throws Fault    { 
        LOG.info("Executing operation fireCue");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_1.FireCueResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#setAutoPlaybackMode2(com.dolby.dcinema.ws.smi.v1_3.SetAutoPlaybackMode2Request  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_3.SetAutoPlaybackMode2Response setAutoPlaybackMode2(com.dolby.dcinema.ws.smi.v1_3.SetAutoPlaybackMode2Request parameters) throws Fault    { 
        LOG.info("Executing operation setAutoPlaybackMode2");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_3.SetAutoPlaybackMode2Response _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getAutoPlaybackMode(com.dolby.dcinema.ws.smi.v1_1.GetAutoPlaybackModeRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_1.GetAutoPlaybackModeResponse getAutoPlaybackMode(com.dolby.dcinema.ws.smi.v1_1.GetAutoPlaybackModeRequest parameters) throws Fault    { 
        LOG.info("Executing operation getAutoPlaybackMode");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_1.GetAutoPlaybackModeResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getPurgeSuiteState(com.dolby.dcinema.ws.smi.v1_1.GetPurgeSuiteStateRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_1.GetPurgeSuiteStateResponse getPurgeSuiteState(com.dolby.dcinema.ws.smi.v1_1.GetPurgeSuiteStateRequest parameters) throws Fault    { 
        LOG.info("Executing operation getPurgeSuiteState");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_1.GetPurgeSuiteStateResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getPlaybackState(com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse getPlaybackState(com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateRequest parameters) throws Fault    { 
        LOG.info("Executing operation getPlaybackState");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.GetPlaybackStateResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#setScheduleMode(com.dolby.dcinema.ws.smi.v1_0.SetScheduleModeRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.SetScheduleModeResponse setScheduleMode(com.dolby.dcinema.ws.smi.v1_0.SetScheduleModeRequest parameters) throws Fault    { 
        LOG.info("Executing operation setScheduleMode");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.SetScheduleModeResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#next(com.dolby.dcinema.ws.smi.v1_0.NextRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.NextResponse next(com.dolby.dcinema.ws.smi.v1_0.NextRequest parameters) throws Fault    { 
        LOG.info("Executing operation next");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.NextResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getAutoPlaybackMode2(com.dolby.dcinema.ws.smi.v1_3.GetAutoPlaybackMode2Request  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_3.GetAutoPlaybackMode2Response getAutoPlaybackMode2(com.dolby.dcinema.ws.smi.v1_3.GetAutoPlaybackMode2Request parameters) throws Fault    { 
        LOG.info("Executing operation getAutoPlaybackMode2");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_3.GetAutoPlaybackMode2Response _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#autoInputSelect(com.dolby.dcinema.ws.smi.v1_4.AutoInputSelectRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_4.AutoInputSelectResponse autoInputSelect(com.dolby.dcinema.ws.smi.v1_4.AutoInputSelectRequest parameters) throws Fault    { 
        LOG.info("Executing operation autoInputSelect");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_4.AutoInputSelectResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getInputSelect(com.dolby.dcinema.ws.smi.v1_4.GetInputSelectRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_4.GetInputSelectResponse getInputSelect(com.dolby.dcinema.ws.smi.v1_4.GetInputSelectRequest parameters) throws Fault    { 
        LOG.info("Executing operation getInputSelect");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_4.GetInputSelectResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#getInputContentProperties(com.dolby.dcinema.ws.smi.v1_4.GetInputContentPropertiesRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_4.GetInputContentPropertiesResponse getInputContentProperties(com.dolby.dcinema.ws.smi.v1_4.GetInputContentPropertiesRequest parameters) throws Fault    { 
        LOG.info("Executing operation getInputContentProperties");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_4.GetInputContentPropertiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#pause(com.dolby.dcinema.ws.smi.v1_0.PauseRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.PauseResponse pause(com.dolby.dcinema.ws.smi.v1_0.PauseRequest parameters) throws Fault    { 
        LOG.info("Executing operation pause");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.PauseResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#stop(com.dolby.dcinema.ws.smi.v1_0.StopRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.StopResponse stop(com.dolby.dcinema.ws.smi.v1_0.StopRequest parameters) throws Fault    { 
        LOG.info("Executing operation stop");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.StopResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#setPlaybackMode2(com.dolby.dcinema.ws.smi.v1_3.SetPlaybackMode2Request  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_3.SetPlaybackMode2Response setPlaybackMode2(com.dolby.dcinema.ws.smi.v1_3.SetPlaybackMode2Request parameters) throws Fault    { 
        LOG.info("Executing operation setPlaybackMode2");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_3.SetPlaybackMode2Response _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#setPlaybackMode(com.dolby.dcinema.ws.smi.v1_0.SetPlaybackModeRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.SetPlaybackModeResponse setPlaybackMode(com.dolby.dcinema.ws.smi.v1_0.SetPlaybackModeRequest parameters) throws Fault    { 
        LOG.info("Executing operation setPlaybackMode");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.SetPlaybackModeResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#previous(com.dolby.dcinema.ws.smi.v1_0.PreviousRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.PreviousResponse previous(com.dolby.dcinema.ws.smi.v1_0.PreviousRequest parameters) throws Fault    { 
        LOG.info("Executing operation previous");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.PreviousResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

    /* (non-Javadoc)
     * @see com.dolby.dcinema.ws.smi.v1.PlaybackControlServiceV14#selectContent(com.dolby.dcinema.ws.smi.v1_0.SelectContentRequest  parameters )*
     */
    public com.dolby.dcinema.ws.smi.v1_0.SelectContentResponse selectContent(com.dolby.dcinema.ws.smi.v1_0.SelectContentRequest parameters) throws Fault    { 
        LOG.info("Executing operation selectContent");
        System.out.println(parameters);
        try {
            com.dolby.dcinema.ws.smi.v1_0.SelectContentResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Fault("fault...");
    }

}