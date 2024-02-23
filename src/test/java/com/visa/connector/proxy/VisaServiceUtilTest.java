package com.visa.connector.proxy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import javax.net.ssl.SSLSocketFactory;

/*
Defines test method for methods in VisaServiceProxy class
 */
@SpringBootTest
public class VisaServiceUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(VisaServiceUtilTest.class);

    @Autowired
    private VisaServiceUtil util;

    @Test
    public void testGetSSLSocketFactory() {
        SSLSocketFactory factory = null;
        try{
            factory = util.getSSLSocketFactory();
        } catch (Exception e) {
            fail(e);
        }

        assertNotNull(factory);
    }

    @Test
    public void testGetAuthorization() {
        String expected = "Basic " +
                "RUNFWE9DSDNEOUNWVzFJNkFIM0kyMTRlc1JOU0tVaVUzV25Cb2o2TWtYdllkWFJFSTpDZm51MzI4Ulk2WWZ4V1M2VA==";
        String auth = util.getAuthorization();
        assertEquals(expected, auth);
    }

    // case: bad httpconnection
    @Test
    public void testGetResponseContent() {
        String res = null;
        try {
            res = util.getResponseContent(null, 200);
        } catch (Exception e) {
            fail(e);
        }
        assertNull(res);
    }
}
