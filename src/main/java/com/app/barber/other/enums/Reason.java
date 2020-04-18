package com.app.barber.other.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Reason {
    VULGAR_NAME(1),
    VULGAR_REVIEW(2),
    OTHER(3);

    private final Integer value;

    Reason(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Optional<Reason> fromValue(Integer value){
        return Arrays.stream(Reason.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst();
    }
}
