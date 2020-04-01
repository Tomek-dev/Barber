package com.app.barber.service;

import com.app.barber.dao.TokenDao;
import com.app.barber.dao.UserDao;
import com.app.barber.model.Token;
import com.app.barber.model.User;
import com.app.barber.other.builder.UserBuilder;
import com.app.barber.other.dto.ForgotInputDto;
import com.app.barber.other.dto.PasswordDto;
import com.app.barber.other.dto.ResetInputDto;
import com.app.barber.other.exception.TokenNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PasswordServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenDao tokenDao;

    @InjectMocks
    private PasswordService passwordService;

    @Test
    public void shouldThrowUsernameNotFoundException(){
        //given
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", null, null);
        given(userDao.findByUsername(Mockito.any(String.class))).willReturn(Optional.empty());

        //then
        assertThrows(UsernameNotFoundException.class, () -> passwordService.change(new PasswordDto(), ""));
        assertThrows(UsernameNotFoundException.class, () -> passwordService.createReset(new ForgotInputDto()));
    }

    @Test
    public void shouldThrowTokenNotFoundException(){
        //given
        given(tokenDao.findByToken(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(TokenNotFoundException.class, () -> passwordService.reset(new ResetInputDto(), UUID.randomUUID().toString()));
    }

    @Test
    public void shouldCreateReset(){
        //given
        User user = new User();
        given(userDao.findByUsername(Mockito.any())).willReturn(Optional.of(user));

        //when
        passwordService.createReset(new ForgotInputDto());

        //then
        verify(tokenDao).save(any());
    }

    @Test
    public void shouldChange(){
        //given
        User user = UserBuilder.builder()
                .password(passwordEncoder.encode("password"))
                .build();
        PasswordDto password = new PasswordDto();
        password.setPassword("change");
        given(userDao.findByUsername(Mockito.any())).willReturn(Optional.of(user));

        //when
        passwordService.change(password, "");

        //then
        verify(userDao).save(user);
    }

    @Test
    public void shouldReset(){
        //given
        Token token = new Token.Builder()
                .user(new User())
                .build();
        ResetInputDto reset = new ResetInputDto();
        reset.setPassword("password");
        given(tokenDao.findByToken(Mockito.any())).willReturn(Optional.of(token));

        //when
        passwordService.reset(reset, UUID.randomUUID().toString());

        //then
        verify(tokenDao).delete(token);
        verify(userDao).save(token.getUser());
    }
}