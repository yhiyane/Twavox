//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.07.17 à 04:41:19 PM CEST 
//


package com.cineapps.server.gdc.status;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cineapps.server.gdc.status package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cineapps.server.gdc.status
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link Response.Status }
     * 
     */
    public Response.Status createResponseStatus() {
        return new Response.Status();
    }

    /**
     * Create an instance of {@link Response.Status.ShowPosition }
     * 
     */
    public Response.Status.ShowPosition createResponseStatusShowPosition() {
        return new Response.Status.ShowPosition();
    }

    /**
     * Create an instance of {@link Response.Status.CplPosition }
     * 
     */
    public Response.Status.CplPosition createResponseStatusCplPosition() {
        return new Response.Status.CplPosition();
    }

}
