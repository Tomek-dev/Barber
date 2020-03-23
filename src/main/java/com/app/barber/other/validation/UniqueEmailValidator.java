package com.app.barber.other.validation;

import com.app.barber.dao.UserDao;
import com.app.barber.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserDao userDao;

    @Autowired
    public UniqueEmailValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userDao.findByEmail(s);
        return !user.isPresent();
    }
}
