package com.app.barber.other.validation;

public class OAuth2AuthenticationProcessingException extends RuntimeException {

    public OAuth2AuthenticationProcessingException() {
        super();
    }

    public OAuth2AuthenticationProcessingException(String message) {
        super(message);
    }

    public OAuth2AuthenticationProcessingException(Throwable cause) {
        super(cause);
    }
}
