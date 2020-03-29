package com.app.barber.other.exception;

public class IncorrectParamException extends RuntimeException {
    public IncorrectParamException() {
        super("Incorrect param");
    }

    public IncorrectParamException(String message) {
        super(message);
    }

    public IncorrectParamException(Throwable cause) {
        super(cause);
    }
}
