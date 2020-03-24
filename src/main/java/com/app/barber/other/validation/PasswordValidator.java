package com.app.barber.other.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, PasswordDetails> {
    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(PasswordDetails passwordDetails, ConstraintValidatorContext constraintValidatorContext) {
        return passwordDetails.getPassword().equals(passwordDetails.getConfirmPassword());
    }
}
