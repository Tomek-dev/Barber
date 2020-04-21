package com.app.barber.other.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Star {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int number;

    public static Optional<Star> fromNumber(int number){
        return Arrays.stream(Star.values())
                .filter(value -> value.getNumber() == number)
                .findFirst();
    }
}
