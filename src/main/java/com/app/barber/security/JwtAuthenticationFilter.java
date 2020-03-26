package com.app.barber.security;

import com.app.barber.dao.UserDao;
import com.app.barber.model.User;
import com.app.barber.other.enums.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret.key}")
    private String secret;

    private UserDao userDao;

    @Autowired
    public JwtAuthenticationFilter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(TOKEN_HEADER);
        if(header == null || !header.startsWith(TOKEN_PREFIX)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        DecodedJWT verified = JWT
                .require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(header.replace(TOKEN_PREFIX, ""));
        String username = verified.getSubject();
        List<String> roles = verified.getClaim("roles").asList(String.class);
        Set<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        Optional<User> userOptional = userDao.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        return authenticationToken;
    }
}
