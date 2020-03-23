package com.app.barber.service;

import com.app.barber.dao.UserDao;
import com.app.barber.model.User;
import com.app.barber.other.builder.UserBuilder;
import com.app.barber.other.dto.SignUpDto;
import com.app.barber.other.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void add(SignUpDto signUp){
        User user = UserBuilder.builder()
                .username(signUp.getUsername())
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .roles(Collections.singleton(Role.USER))
                .build();
        userDao.save(user);
    }

    public void delete(Long id){
        userDao.deleteById(id);
    }
}
