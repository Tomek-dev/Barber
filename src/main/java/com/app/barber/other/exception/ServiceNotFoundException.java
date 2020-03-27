package com.app.barber.other.exception;

public class ServiceNotFoundException extends RuntimeException{
    public ServiceNotFoundException() {
        super("Service not found");
    }

    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException(Throwable cause) {
        super(cause);
    }
}
