package com.app.barber.other.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Star {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private final int number;

    Star(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static Optional<Star> fromNumber(int number){
        return Arrays.stream(Star.values())
                .filter(value -> value.getNumber() == number)
                .findFirst();
    }
}
