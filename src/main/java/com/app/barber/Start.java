package com.app.barber;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ReviewDao;
import com.app.barber.dao.UserDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Review;
import com.app.barber.model.User;
import com.app.barber.other.builder.BarberBuidler;
import com.app.barber.other.builder.ReviewBuilder;
import com.app.barber.other.builder.UserBuilder;
import com.app.barber.other.enums.Role;
import com.app.barber.other.enums.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Start {

    private ReviewDao reviewDao;
    private BarberDao barberDao;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Start(ReviewDao reviewDao, BarberDao barberDao, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.reviewDao = reviewDao;
        this.barberDao = barberDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    private void init(){
        Barber barber = new Barber();
        Review review = ReviewBuilder.builder()
                .review("review")
                .star(Star.FOUR)
                .barber(barber)
                .build();
        User user = UserBuilder.builder()
                .username("user")
                .email("email")
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton(Role.USER))
                .build();
        barber.setUser(user);
        reviewDao.save(review);
        barberDao.save(barber);
        userDao.save(user);
    }
}
