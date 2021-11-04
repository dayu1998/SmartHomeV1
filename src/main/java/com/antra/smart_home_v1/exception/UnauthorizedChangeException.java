package com.antra.smart_home_v1.exception;

public class UnauthorizedChangeException extends RuntimeException{
    public UnauthorizedChangeException() {
    }

    public UnauthorizedChangeException(String message) {
        super(message);
    }
}
