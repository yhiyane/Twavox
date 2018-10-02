
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dolby.dcinema.ws.smi.v1.schemas.common package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dolby.dcinema.ws.smi.v1.schemas.common
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InfoPropertyType }
     * 
     */
    public InfoPropertyType createInfoPropertyType() {
        return new InfoPropertyType();
    }

    /**
     * Create an instance of {@link DeviceComponentType }
     * 
     */
    public DeviceComponentType createDeviceComponentType() {
        return new DeviceComponentType();
    }

    /**
     * Create an instance of {@link ContentType }
     * 
     */
    public ContentType createContentType() {
        return new ContentType();
    }

    /**
     * Create an instance of {@link TransportState }
     * 
     */
    public TransportState createTransportState() {
        return new TransportState();
    }

    /**
     * Create an instance of {@link TransportStateType }
     * 
     */
    public TransportStateType createTransportStateType() {
        return new TransportStateType();
    }

    /**
     * Create an instance of {@link TransportStateUnavailableReason }
     * 
     */
    public TransportStateUnavailableReason createTransportStateUnavailableReason() {
        return new TransportStateUnavailableReason();
    }

    /**
     * Create an instance of {@link LicenseState }
     * 
     */
    public LicenseState createLicenseState() {
        return new LicenseState();
    }

    /**
     * Create an instance of {@link ContentStoreType }
     * 
     */
    public ContentStoreType createContentStoreType() {
        return new ContentStoreType();
    }

    /**
     * Create an instance of {@link Rating }
     * 
     */
    public Rating createRating() {
        return new Rating();
    }

}
