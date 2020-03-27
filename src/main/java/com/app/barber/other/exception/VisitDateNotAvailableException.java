package com.app.barber.other.exception;

public class VisitDateNotAvailableException extends RuntimeException {
    public VisitDateNotAvailableException() {
        super("Visit date not available");
    }

    public VisitDateNotAvailableException(String message) {
        super(message);
    }

    public VisitDateNotAvailableException(Throwable cause) {
        super(cause);
    }
}
