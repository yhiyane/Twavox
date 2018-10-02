
package com.dolby.dcinema.ws.smi.v1.schemas.fault;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dolby.dcinema.ws.smi.v1.schemas.fault package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dolby.dcinema.ws.smi.v1.schemas.fault
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Fault }
     * 
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link NotConnected }
     * 
     */
    public NotConnected createNotConnected() {
        return new NotConnected();
    }

    /**
     * Create an instance of {@link NotReady }
     * 
     */
    public NotReady createNotReady() {
        return new NotReady();
    }

    /**
     * Create an instance of {@link NotFound }
     * 
     */
    public NotFound createNotFound() {
        return new NotFound();
    }

    /**
     * Create an instance of {@link ContentFailure }
     * 
     */
    public ContentFailure createContentFailure() {
        return new ContentFailure();
    }

    /**
     * Create an instance of {@link ContentStoreFailure }
     * 
     */
    public ContentStoreFailure createContentStoreFailure() {
        return new ContentStoreFailure();
    }

    /**
     * Create an instance of {@link DeviceComponentFailure }
     * 
     */
    public DeviceComponentFailure createDeviceComponentFailure() {
        return new DeviceComponentFailure();
    }

    /**
     * Create an instance of {@link InvalidArgumentFailure }
     * 
     */
    public InvalidArgumentFailure createInvalidArgumentFailure() {
        return new InvalidArgumentFailure();
    }

    /**
     * Create an instance of {@link InvalidAuditorium }
     * 
     */
    public InvalidAuditorium createInvalidAuditorium() {
        return new InvalidAuditorium();
    }

    /**
     * Create an instance of {@link InvalidTransportStateFailure }
     * 
     */
    public InvalidTransportStateFailure createInvalidTransportStateFailure() {
        return new InvalidTransportStateFailure();
    }

    /**
     * Create an instance of {@link BusyFailure }
     * 
     */
    public BusyFailure createBusyFailure() {
        return new BusyFailure();
    }

    /**
     * Create an instance of {@link OperationNotImplemented }
     * 
     */
    public OperationNotImplemented createOperationNotImplemented() {
        return new OperationNotImplemented();
    }

    /**
     * Create an instance of {@link OperationFailed }
     * 
     */
    public OperationFailed createOperationFailed() {
        return new OperationFailed();
    }

}
