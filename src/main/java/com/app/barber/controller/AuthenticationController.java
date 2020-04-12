package com.app.barber.controller;

import com.app.barber.model.OAuthUser;
import com.app.barber.model.User;
import com.app.barber.other.dto.AuthenticatedDto;
import com.app.barber.other.exception.BadRequestException;
import com.app.barber.other.payload.LoginRequest;
import com.app.barber.other.payload.ApiResponse;
import com.app.barber.other.payload.SignUpRequest;
import com.app.barber.other.payload.TokenResponse;
import com.app.barber.security.jwt.JwtTokenProvider;
import com.app.barber.service.OAuthUserService;
import com.app.barber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider provider;
    private OAuthUserService oAuthUserService;

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider provider, OAuthUserService oAuthUserService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.provider = provider;
        this.oAuthUserService = oAuthUserService;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/authenticated")
    public AuthenticatedDto authenticated(Authentication authentication, Principal principal){
        if(authentication.getPrincipal() instanceof User){
            return userService.authenticated((User) authentication.getPrincipal());
        }
        if(authentication.getPrincipal() instanceof OAuthUser){
            return oAuthUserService.authenticated((OAuthUser) authentication.getPrincipal());
        }
        throw new BadRequestException();
    }

}
