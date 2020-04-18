package com.app.barber.service;

import com.app.barber.dao.UserDao;
import com.app.barber.model.User;
import com.app.barber.other.builder.UserBuilder;
import com.app.barber.other.dto.AuthenticatedDto;
import com.app.barber.other.dto.AvailabilityDto;
import com.app.barber.other.payload.SignUpRequest;
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

    public void add(SignUpRequest signUp){
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

    public boolean existsByEmailIgnoreCase(String email){
        return userDao.existsByEmailIgnoreCase(email);
    }

    public boolean existsByUsernameIgnoreCase(String username){
        return userDao.existsByUsernameIgnoreCase(username);
    }

    public AvailabilityDto usernameAvailability(String username){
        Boolean available = userDao.existsByUsernameIgnoreCase(username);
        return new AvailabilityDto(!available);
    }

    public AvailabilityDto emailAvailability(String email){
        Boolean available = userDao.existsByEmailIgnoreCase(email);
        return new AvailabilityDto(!available);
    }


    public AuthenticatedDto authenticated(User user) {
        AuthenticatedDto authenticatedDto = new AuthenticatedDto();
        authenticatedDto.setEmail(user.getEmail());
        authenticatedDto.setName(user.getUsername());
        authenticatedDto.setType("basic");
        authenticatedDto.setId(user.getId());
        authenticatedDto.setBarberId(user.getBarber().getId());
        return authenticatedDto;
    }
}
