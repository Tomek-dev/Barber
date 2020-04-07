package com.app.barber.other.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Reason {
    VULGAR_NAME("vulgar name"),
    VULGAR_REVIEW("vulgar review"),
    OTHER("name");

    private final String value;

    Reason(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<Reason> fromValue(String value){
        return Arrays.stream(Reason.values()).
                filter(type -> type.getValue().equalsIgnoreCase(value))
                .findFirst();
    }
}
