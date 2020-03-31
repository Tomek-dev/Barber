package com.app.barber.controller;

import com.app.barber.other.payload.LoginRequest;
import com.app.barber.other.payload.ApiResponse;
import com.app.barber.other.payload.SignUpRequest;
import com.app.barber.other.payload.TokenResponse;
import com.app.barber.security.JwtTokenProvider;
import com.app.barber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest login){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = provider.generateToken(authentication);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUp){
        userService.add(signUp);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully."));
    }
}
