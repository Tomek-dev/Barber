package com.app.barber.other.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum Reason {
    VULGAR_NAME(1),
    VULGAR_REVIEW(2),
    OTHER(3);

    private final Integer value;

    public static Optional<Reason> fromValue(Integer value){
        return Arrays.stream(Reason.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst();
    }
}
