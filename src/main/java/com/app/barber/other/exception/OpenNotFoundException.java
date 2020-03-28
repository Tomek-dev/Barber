package com.app.barber.other.exception;

public class OpenNotFoundException extends RuntimeException {
    public OpenNotFoundException() {
        super("Open not found");
    }

    public OpenNotFoundException(String message) {
        super(message);
    }

    public OpenNotFoundException(Throwable cause) {
        super(cause);
    }
}
