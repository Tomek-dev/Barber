package com.app.barber.other.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TodayValidator implements ConstraintValidator<Today, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDateTime.parse(s).compareTo(LocalDateTime.now()) > 0;
    }

    @Override
    public void initialize(Today constraintAnnotation) {

    }
}
