package com.app.barber.other.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException() {
        super("Report not found");
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(Throwable cause) {
        super(cause);
    }
}
