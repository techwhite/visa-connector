package com.visa.connector.controller;

import com.visa.connector.interceptor.Debug;
import com.visa.connector.model.ResponseWrapper;
import com.visa.connector.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 provide restful api endpoints for Connection request
 */
@RestController
@RequestMapping("/connection")
public class ConnectionController {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionController.class);

    @Autowired
    private ConnectionService service;

    /*
    connect method is used for process requests from GET /connection
     */
    @Debug
    @GetMapping
    public ResponseWrapper connect() throws Exception{
        logger.info("Connect request received");

        // call service layer
        String res = service.request();

        return new ResponseWrapper(HttpStatus.OK,
                res);
    }
}
