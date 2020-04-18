package com.app.barber;

import com.app.barber.dao.*;
import com.app.barber.model.*;
import com.app.barber.other.builder.*;
import com.app.barber.other.enums.AuthProvider;
import com.app.barber.other.enums.Role;
import com.app.barber.other.enums.SocialType;
import com.app.barber.other.enums.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class Start {

    private ReviewDao reviewDao;
    private BarberDao barberDao;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private WorkerDao workerDao;
    private ServiceDao serviceDao;
    private OpenDao openDao;
    private SocialDao socialDao;
    private OAuthUserDao oAuthUserDao;
    private VisitDao visitDao;

    @Autowired
    public Start(ReviewDao reviewDao, BarberDao barberDao,
                 UserDao userDao, PasswordEncoder passwordEncoder,
                 WorkerDao workerDao, ServiceDao serviceDao,
                 OpenDao openDao, SocialDao socialDao, OAuthUserDao oAuthUserDao,
                 VisitDao visitDao) {
        this.reviewDao = reviewDao;
        this.barberDao = barberDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.workerDao = workerDao;
        this.serviceDao = serviceDao;
        this.openDao = openDao;
        this.socialDao = socialDao;
        this.oAuthUserDao = oAuthUserDao;
        this.visitDao = visitDao;
        init();
    }

    private void init(){
        Barber barber = BarberBuidler.buidler()
                .name("name")
                .local("1")
                .city("city")
                .address("address")
                .latitude(52.237049)
                .longitude(21.017532)
                .build();
        OAuthUser oAuthUser = OAuthUserBuilder.builder()
                .provider(AuthProvider.FACEBOOK)
                .name("name")
                .build();
        User user = UserBuilder.builder()
                .username("user")
                .email("user@email.email")
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton(Role.USER))
                .build();
        barber.setUser(user);
        List<Worker> workers = new LinkedList<>();
        for(int i = 0; i < 4; i++){
            Worker worker = WorkerBuilder.builder()
                    .name("name")
                    .barber(barber)
                    .build();
            workers.add(worker);
        }
        Service service = ServiceBuilder.builder()
                .name("name")
                .price(1.0)
                .description("description")
                .workers(Collections.singleton(workers.get(0)))
                .time(30L)
                .barber(barber)
                .build();
        workers.get(0).setServices(Collections.singleton(service));
        Review review = ReviewBuilder.builder()
                .review("review")
                .star(Star.FOUR)
                .worker(workers.get(0))
                .service(service)
                .owner(oAuthUser)
                .barber(barber)
                .build();
        Visit visit = VisitBuilder.builder()
                .beginning(LocalDateTime.parse("2020-03-29T10:30:01.0"))
                .finish(LocalDateTime.parse("2020-03-29T11:00:00.0"))
                .service(service)
                .worker(workers.get(0))
                .barber(barber)
                .name("name")
                .build();
        List<Open> opens = new LinkedList<>();
        for (DayOfWeek value : DayOfWeek.values()) {
            Open open = OpenBuilder.builder()
                    .open(LocalTime.of(8, 0))
                    .close(LocalTime.of(18, 0))
                    .day(value)
                    .barber(barber)
                    .build();
            opens.add(open);
        }
        Social social = SocialBuilder.builder()
                .type(SocialType.FACEBOOK)
                .url("url")
                .barber(barber)
                .build();
        userDao.save(user);
        barberDao.save(barber);
        workerDao.saveAll(workers);
        serviceDao.save(service);
        openDao.saveAll(opens);
        socialDao.save(social);
        oAuthUserDao.save(oAuthUser);
        reviewDao.save(review);
        visitDao.save(visit);
        User newUser = UserBuilder.builder()
                .roles(Collections.singleton(Role.USER))
                .username("new")
                .password(passwordEncoder.encode("password"))
                .email("new@email.email")
                .build();
        userDao.save(newUser);
    }
}
