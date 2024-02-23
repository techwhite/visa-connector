package com.visa.connector.proxy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
Defines test method for methods in VisaServiceProxy class
 */
@SpringBootTest
public class VisaServiceProxyTest {
    private static final Logger logger = LoggerFactory.getLogger(VisaServiceProxyTest.class);

    @Autowired
    private VisaServiceProxy proxy;

    @Test
    public void testHelloWord() {
        try {
            String res = proxy.helloWorld();
            assertTrue(res != null && res.contains("helloworld"));
        } catch (Exception e) {
            fail(e);
        }
    }
}
