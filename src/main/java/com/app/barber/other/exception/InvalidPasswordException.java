package com.app.barber.other.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Invalid password");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(Throwable cause) {
        super(cause);
    }
}
