package com.visa.connector.proxy;

import com.visa.connector.interceptor.Debug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;

/*
VisaServiceProxy is a layer to interact with dependent service.
Here is external visa service. The url is defined as visa.service.endpoint.url in application.properties
 */
@Component
public class VisaServiceProxy {
    private static final Logger logger = LoggerFactory.getLogger(VisaServiceProxy.class);

    @Autowired
    private VisaServiceUtil util;

    @Value("${visa.service.endpoint.url}")
    private String endpointUrl;

    @Debug
    public String helloWorld() throws Exception {
        logger.debug("START Two-Way (Mutual) SSL ...");

        // open http connection
        URL url = new URL(endpointUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // cast to https type
        if (con instanceof HttpsURLConnection) {
            ((HttpsURLConnection) con).setSSLSocketFactory(util.getSSLSocketFactory());
        }

        // set header info
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", util.getAuthorization());

        // send
        int status = con.getResponseCode();
        if (status != 200) {
            logger.error("Two-Way (Mutual) SSL test failed, status:" + status);
        } else {
            logger.info("Http Status: " + status);
        }

        // parse response
        String result = util.getResponseContent(con, status);
        con.disconnect();

        logger.debug("END Two-Way (Mutual) SSL ...");

        return result;
    }
}
