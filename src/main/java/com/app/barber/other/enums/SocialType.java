package com.app.barber.other.enums;

public enum SocialType {
    FACEBOOK("Facebook"),
    TWITTER("Twitter"),
    MESSENGER("Messenger"),
    INSTAGRAM("Instagram");

    private final String value;

    SocialType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
