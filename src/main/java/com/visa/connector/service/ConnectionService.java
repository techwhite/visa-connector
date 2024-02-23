package com.visa.connector.service;

import com.visa.connector.proxy.VisaServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
This class defines methods for processing Connections business logic
 */

@Service
public class ConnectionService {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionService.class);

    @Autowired
    private VisaServiceProxy visaServiceProxy;

    /*
    Here except calling to proxy layer, we can add more business logic processing
     */
    public String request() throws Exception{
        String res = visaServiceProxy.helloWorld();
        // add more business logic
        return res;
    }
}
