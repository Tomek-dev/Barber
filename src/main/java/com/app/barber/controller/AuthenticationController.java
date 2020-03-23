package com.app.barber.controller;

import com.app.barber.other.dto.LoginDto;
import com.app.barber.other.dto.SignUpDto;
import com.app.barber.security.JwtTokenProvider;
import com.app.barber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider provider;

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider provider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.provider = provider;
    }

    public ResponseEntity login(@Valid @RequestBody LoginDto login){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = provider.generateToken(authentication);
        //TODO return
        return null;
    }

    public ResponseEntity signUp(@Valid @RequestBody SignUpDto signUp){

        //TODO validate and return
        userService.add(signUp);
        return null;
    }
}
