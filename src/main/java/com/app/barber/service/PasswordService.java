package com.app.barber.service;

import com.app.barber.dao.TokenDao;
import com.app.barber.dao.UserDao;
import com.app.barber.model.Token;
import com.app.barber.model.User;
import com.app.barber.other.dto.ForgotInputDto;
import com.app.barber.other.dto.PasswordDto;
import com.app.barber.other.dto.ResetInputDto;
import com.app.barber.other.exception.InvalidPasswordException;
import com.app.barber.other.exception.TokenNotFoundException;
import com.app.barber.other.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private TokenDao tokenDao;

    @Autowired
    public PasswordService(UserDao userDao, PasswordEncoder passwordEncoder, TokenDao tokenDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.tokenDao = tokenDao;
    }

    public void change(PasswordDto password, User user){
        if(!passwordEncoder.matches(password.getOldPass(), user.getPassword())){
            throw new InvalidPasswordException();
        }
        user.setPassword(passwordEncoder.encode(password.getNewPass()));
        userDao.save(user);
    }

    public void reset(ResetInputDto reset, String token){
        Optional<Token> tokenOptional = tokenDao.findByToken(UUID.fromString(token));
        Token foundToken = tokenOptional.orElseThrow(TokenNotFoundException::new);
        User user = foundToken.getUser();
        user.setPassword(passwordEncoder.encode(reset.getPassword()));
        userDao.save(user);
        tokenDao.delete(foundToken);
    }

    public void createReset(ForgotInputDto forgot){
        if (!tokenDao.existsByUserUsername(forgot.getUsername())) {
            Optional<User> userOptional = userDao.findByUsername(forgot.getUsername());
            User user = userOptional.orElseThrow(UserNotFoundException::new);
            Token token = new Token.Builder()
                    .user(user)
                    .token(UUID.randomUUID())
                    .build();
            tokenDao.save(token);
        }
    }
}
