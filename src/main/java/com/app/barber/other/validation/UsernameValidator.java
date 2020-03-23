package com.app.barber.other.validation;

import com.app.barber.dao.UserDao;
import com.app.barber.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private UserDao userDao;

    @Autowired
    public UsernameValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void initialize(Username constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userDao.findByUsername(s);
        return !user.isPresent();
    }
}
