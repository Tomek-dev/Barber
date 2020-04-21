package com.app.barber.other.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SocialType {
    FACEBOOK("Facebook"),
    TWITTER("Twitter"),
    MESSENGER("Messenger"),
    INSTAGRAM("Instagram");

    private final String value;
}
