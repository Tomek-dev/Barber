package com.app.barber.other.validation;

import com.app.barber.dao.UserDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserDao userDao;

    public UniqueEmailValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userDao.existsByEmailIgnoreCase(s);
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }
}
