package com.app.barber.other.exception;

public class GeocodeException extends RuntimeException {
    public GeocodeException() {
        super("Cannot geocode by address");
    }

    public GeocodeException(String message) {
        super(message);
    }

    public GeocodeException(Throwable cause) {
        super(cause);
    }
}
