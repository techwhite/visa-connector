package com.visa.connector.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/*
used to return more debug information to client side
 */
@Getter
@Setter
public class ResponseWrapper {
    private int code;

    private String status;

    private String message;

    private String stackTrace;

    private Object data;

    public ResponseWrapper() {
    }

    public ResponseWrapper(
            HttpStatus httpStatus,
            String message
    ) {
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    public ResponseWrapper(
            HttpStatus httpStatus,
            String message,
            String stackTrace
    ) {
        this(
                httpStatus,
                message
        );

        this.stackTrace = stackTrace;
    }

    public ResponseWrapper(
            HttpStatus httpStatus,
            String message,
            String stackTrace,
            Object data
    ) {
        this(
                httpStatus,
                message,
                stackTrace
        );

        this.data = data;
    }
}