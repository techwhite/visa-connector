package com.visa.connector.exception;

import com.visa.connector.model.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

/*
 Global exception handling involves the centralization of exception handling logic.
 @ControllerAdvice allows the definition of global exception handlers that apply to multiple
 controllers.
 Global exception handling defines how the application should respond to different types of exceptions.  It
 includes logging the error, providing custom error messages, and returning specific HTTP status codes.
 */
@ControllerAdvice
public class GlobalExceptionalHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionalHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseWrapper handleException(Exception e) {

        // read stackTrace from exception
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        logger.error(stackTrace);

        return new ResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), stackTrace);
    }
}
