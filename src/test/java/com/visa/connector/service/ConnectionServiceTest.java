package com.visa.connector.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
defines test for methods in class ConnectionService
 */
@SpringBootTest
public class ConnectionServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionServiceTest.class);

    @Autowired
    private ConnectionService service;

    @Test
    public void testGetAllConnections() {
        String res = null;
        try {
            res = service.request();
        } catch (Exception e) {
            fail(e);
        }

        assertTrue(res != null && res.contains("helloworld"));
    }
}
