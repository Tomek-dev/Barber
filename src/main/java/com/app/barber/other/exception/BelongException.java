package com.app.barber.other.exception;

public class BelongException extends RuntimeException {
    public BelongException() {
        super("This service not belong to worker");
    }

    public BelongException(String message) {
        super(message);
    }

    public BelongException(Throwable cause) {
        super(cause);
    }
}
