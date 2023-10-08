package com.vr.miniauth.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -1719640807238657211L;

    private HttpStatus status;

    private String message;

    public BaseException(HttpStatus status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
