package com.app.barber.other.exception;

public class SocialNotFoundException extends RuntimeException {
    public SocialNotFoundException() {
        super("Social not found");
    }

    public SocialNotFoundException(String message) {
        super(message);
    }

    public SocialNotFoundException(Throwable cause) {
        super(cause);
    }
}
