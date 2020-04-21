package com.app.barber.other.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthProvider {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    GITHUB("github");

    private final String value;
}
