package com.app.barber.other.enums;

public enum AuthProvider {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    GITHUB("github");

    private final String value;

    AuthProvider(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
