
package com.dolby.dcinema.ws.smi.v1;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.1
 * 2015-07-09T17:38:11.780+02:00
 * Generated source version: 3.1.1
 * 
 */
 
public class PlaybackControlServiceV10_PlaybackControlServiceV10_Server{

    protected PlaybackControlServiceV10_PlaybackControlServiceV10_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new PlaybackControlService_V1_0Impl2();
        String address = "http://www.dolby.com:8080/dcinema/ws/smi/v1/PlaybackControlService";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new PlaybackControlServiceV10_PlaybackControlServiceV10_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
