package com.app.barber.other.exception;

public class OAuthUserNotFoundException extends RuntimeException{
    public OAuthUserNotFoundException() {
        super("OAuth user not found");
    }

    public OAuthUserNotFoundException(String message) {
        super(message);
    }

    public OAuthUserNotFoundException(Throwable cause) {
        super(cause);
    }
}
