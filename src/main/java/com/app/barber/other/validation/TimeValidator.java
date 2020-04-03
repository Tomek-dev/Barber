package com.app.barber.other.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class TimeValidator implements ConstraintValidator<Time, String> {

    private final int[] times = {0, 15, 30, 45};


    @Override
    public void initialize(Time constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try{
            return Arrays.stream(times).anyMatch(time -> time == LocalTime.parse(s).getMinute());
        }catch (DateTimeParseException e){
            return Arrays.stream(times).anyMatch(time -> time == LocalDateTime.parse(s).getMinute());
        }
    }
}
