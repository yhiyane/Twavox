
package com.dolby.dcinema.ws.smi.v1_1;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dolby.dcinema.ws.smi.v1_1 package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dolby.dcinema.ws.smi.v1_1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSPL2Request }
     * 
     */
    public GetSPL2Request createGetSPL2Request() {
        return new GetSPL2Request();
    }

    /**
     * Create an instance of {@link GetSPL2Response }
     * 
     */
    public GetSPL2Response createGetSPL2Response() {
        return new GetSPL2Response();
    }

}
