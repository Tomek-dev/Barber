package com.app.barber.other.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TodayValidator implements ConstraintValidator<Today, LocalDateTime> {
    @Override
    public void initialize(Today constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        return localDateTime.compareTo(LocalDateTime.now()) > 0;
    }
}
