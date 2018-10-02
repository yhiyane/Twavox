
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ContentPlaybackState }
     * 
     */
    public ContentPlaybackState createContentPlaybackState() {
        return new ContentPlaybackState();
    }

    /**
     * Create an instance of {@link PlaybackMode2 }
     * 
     */
    public PlaybackMode2 createPlaybackMode2() {
        return new PlaybackMode2();
    }

    /**
     * Create an instance of {@link PurgeSuiteState }
     * 
     */
    public PurgeSuiteState createPurgeSuiteState() {
        return new PurgeSuiteState();
    }

    /**
     * Create an instance of {@link AutoPlaybackMode2 }
     * 
     */
    public AutoPlaybackMode2 createAutoPlaybackMode2() {
        return new AutoPlaybackMode2();
    }

    /**
     * Create an instance of {@link InputSelectMode }
     * 
     */
    public InputSelectMode createInputSelectMode() {
        return new InputSelectMode();
    }

    /**
     * Create an instance of {@link PrepSuiteStatus }
     * 
     */
    public PrepSuiteStatus createPrepSuiteStatus() {
        return new PrepSuiteStatus();
    }

}
