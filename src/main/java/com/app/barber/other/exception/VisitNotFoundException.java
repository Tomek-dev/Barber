package com.app.barber.other.exception;

public class VisitNotFoundException extends RuntimeException{
    public VisitNotFoundException() {
        super("Visit bot found");
    }

    public VisitNotFoundException(String message) {
        super(message);
    }

    public VisitNotFoundException(Throwable cause) {
        super(cause);
    }
}
