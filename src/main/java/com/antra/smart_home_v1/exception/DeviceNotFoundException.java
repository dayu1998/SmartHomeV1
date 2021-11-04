package com.antra.smart_home_v1.exception;

public class DeviceNotFoundException extends RuntimeException{
    public DeviceNotFoundException() {
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
