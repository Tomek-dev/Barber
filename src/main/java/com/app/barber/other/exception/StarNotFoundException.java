package com.app.barber.other.exception;

public class StarNotFoundException extends RuntimeException{
    public StarNotFoundException() {
        super("Star not found");
    }

    public StarNotFoundException(String message) {
        super(message);
    }

    public StarNotFoundException(Throwable cause) {
        super(cause);
    }
}
