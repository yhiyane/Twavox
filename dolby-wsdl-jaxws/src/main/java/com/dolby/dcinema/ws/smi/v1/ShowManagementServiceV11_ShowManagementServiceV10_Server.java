
package com.dolby.dcinema.ws.smi.v1;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.1
 * 2015-07-09T17:41:04.099+02:00
 * Generated source version: 3.1.1
 * 
 */
 
public class ShowManagementServiceV11_ShowManagementServiceV10_Server{

    protected ShowManagementServiceV11_ShowManagementServiceV10_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new ShowManagementService_V1_0Impl();
        String address = "http://www.dolby.com:8080/dcinema/ws/smi/v1/ShowManagementService";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new ShowManagementServiceV11_ShowManagementServiceV10_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
