package com.app.barber.other.validation;

import com.app.barber.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private UserDao userDao;

    @Autowired
    public UsernameValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userDao.existsByUsernameIgnoreCase(s);
    }

    @Override
    public void initialize(Username constraintAnnotation) {

    }
}
