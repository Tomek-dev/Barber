package com.app.barber.security;

import com.app.barber.model.User;
import com.app.barber.other.enums.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret.key}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withArrayClaim("roles", (String[]) user.getRoles().stream()
                        .map(Role::getAuthority).distinct().toArray())
                .sign(Algorithm.HMAC512(secret.getBytes()));
        return TOKEN_PREFIX + token;
    }
}
