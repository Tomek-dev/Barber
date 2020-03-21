package com.app.barber.other.exception;

public class BarberNotFoundException extends RuntimeException{
    public BarberNotFoundException() {
        super("Barber not found");
    }

    public BarberNotFoundException(String message) {
        super(message);
    }

    public BarberNotFoundException(Throwable cause) {
        super(cause);
    }
}
