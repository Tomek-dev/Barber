package com.app.barber.other.enums;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Role implements GrantedAuthority {
    USER("ROLE_USER"),
    OAUTH("ROLE_OAUTH");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
