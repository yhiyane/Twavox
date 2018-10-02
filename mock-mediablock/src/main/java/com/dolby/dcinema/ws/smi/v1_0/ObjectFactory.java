
package com.dolby.dcinema.ws.smi.v1_0;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.dolby.dcinema.ws.smi.v1.schemas.fault.Fault;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dolby.dcinema.ws.smi.v1_0 package. 
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

    private final static QName _Fault_QNAME = new QName("http://www.dolby.com/dcinema/ws/smi/v1_0", "fault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dolby.dcinema.ws.smi.v1_0
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetShowsRequest }
     * 
     */
    public GetShowsRequest createGetShowsRequest() {
        return new GetShowsRequest();
    }

    /**
     * Create an instance of {@link GetShowsResponse }
     * 
     */
    public GetShowsResponse createGetShowsResponse() {
        return new GetShowsResponse();
    }

    /**
     * Create an instance of {@link GetShowInfosRequest }
     * 
     */
    public GetShowInfosRequest createGetShowInfosRequest() {
        return new GetShowInfosRequest();
    }

    /**
     * Create an instance of {@link GetShowInfosResponse }
     * 
     */
    public GetShowInfosResponse createGetShowInfosResponse() {
        return new GetShowInfosResponse();
    }

    /**
     * Create an instance of {@link GetSPLRequest }
     * 
     */
    public GetSPLRequest createGetSPLRequest() {
        return new GetSPLRequest();
    }

    /**
     * Create an instance of {@link GetSPLResponse }
     * 
     */
    public GetSPLResponse createGetSPLResponse() {
        return new GetSPLResponse();
    }

    /**
     * Create an instance of {@link GetCueInfosRequest }
     * 
     */
    public GetCueInfosRequest createGetCueInfosRequest() {
        return new GetCueInfosRequest();
    }

    /**
     * Create an instance of {@link GetCueInfosResponse }
     * 
     */
    public GetCueInfosResponse createGetCueInfosResponse() {
        return new GetCueInfosResponse();
    }

    /**
     * Create an instance of {@link CreateShowRequest }
     * 
     */
    public CreateShowRequest createCreateShowRequest() {
        return new CreateShowRequest();
    }

    /**
     * Create an instance of {@link CreateShowResponse }
     * 
     */
    public CreateShowResponse createCreateShowResponse() {
        return new CreateShowResponse();
    }

    /**
     * Create an instance of {@link DeleteShowRequest }
     * 
     */
    public DeleteShowRequest createDeleteShowRequest() {
        return new DeleteShowRequest();
    }

    /**
     * Create an instance of {@link DeleteShowResponse }
     * 
     */
    public DeleteShowResponse createDeleteShowResponse() {
        return new DeleteShowResponse();
    }

    /**
     * Create an instance of {@link GetShowTimesRequest }
     * 
     */
    public GetShowTimesRequest createGetShowTimesRequest() {
        return new GetShowTimesRequest();
    }

    /**
     * Create an instance of {@link GetShowTimesResponse }
     * 
     */
    public GetShowTimesResponse createGetShowTimesResponse() {
        return new GetShowTimesResponse();
    }

    /**
     * Create an instance of {@link GetShowTimeInfosRequest }
     * 
     */
    public GetShowTimeInfosRequest createGetShowTimeInfosRequest() {
        return new GetShowTimeInfosRequest();
    }

    /**
     * Create an instance of {@link GetShowTimeInfosResponse }
     * 
     */
    public GetShowTimeInfosResponse createGetShowTimeInfosResponse() {
        return new GetShowTimeInfosResponse();
    }

    /**
     * Create an instance of {@link CreateShowTimesRequest }
     * 
     */
    public CreateShowTimesRequest createCreateShowTimesRequest() {
        return new CreateShowTimesRequest();
    }

    /**
     * Create an instance of {@link CreateShowTimesResponse }
     * 
     */
    public CreateShowTimesResponse createCreateShowTimesResponse() {
        return new CreateShowTimesResponse();
    }

    /**
     * Create an instance of {@link DeleteShowTimesRequest }
     * 
     */
    public DeleteShowTimesRequest createDeleteShowTimesRequest() {
        return new DeleteShowTimesRequest();
    }

    /**
     * Create an instance of {@link DeleteShowTimesResponse }
     * 
     */
    public DeleteShowTimesResponse createDeleteShowTimesResponse() {
        return new DeleteShowTimesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.dolby.com/dcinema/ws/smi/v1_0", name = "fault")
    public JAXBElement<Fault> createFault(Fault value) {
        return new JAXBElement<Fault>(_Fault_QNAME, Fault.class, null, value);
    }

}
