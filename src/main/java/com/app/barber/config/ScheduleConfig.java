package com.app.barber.config;

import com.app.barber.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    private TokenDao tokenDao;

    @Autowired
    public ScheduleConfig(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Scheduled(fixedRate = 60000)
    private void delete(){
        tokenDao.deleteByDateLessThanEqual(LocalDateTime.now());
    }
}
