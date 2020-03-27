package com.app.barber;

import com.app.barber.dao.*;
import com.app.barber.model.*;
import com.app.barber.other.builder.*;
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
    private WorkerDao workerDao;
    private ServiceDao serviceDao;

    @Autowired
    public Start(ReviewDao reviewDao, BarberDao barberDao, UserDao userDao, PasswordEncoder passwordEncoder, WorkerDao workerDao, ServiceDao serviceDao) {
        this.reviewDao = reviewDao;
        this.barberDao = barberDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.workerDao = workerDao;
        this.serviceDao = serviceDao;
        init();
    }

    private void init(){
        Barber barber = BarberBuidler.buidler()
                .name("name")
                .local("1")
                .city("city")
                .address("address")
                .build();
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
        Worker worker = WorkerBuilder.builder()
                .name("name")
                .barber(barber)
                .build();
        Service service = ServiceBuilder.builder()
                .name("name")
                .price(1.0)
                .description("description")
                .worker(worker)
                .time(10L)
                .build();
        userDao.save(user);
        barberDao.save(barber);
        reviewDao.save(review);
        workerDao.save(worker);
        serviceDao.save(service);
    }
}
