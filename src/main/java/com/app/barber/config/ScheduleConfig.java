package com.app.barber.config;

import com.app.barber.dao.TokenDao;
import com.app.barber.dao.VisitDao;
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
    public ScheduleConfig(TokenDao tokenDao, VisitDao visitDao) {
        this.tokenDao = tokenDao;
    }

    @Scheduled(fixedRate = 60000)
    private void deleteToken(){
        tokenDao.deleteByDateLessThanEqual(LocalDateTime.now());
    }
}
