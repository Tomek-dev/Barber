package com.app.barber.other;

import com.app.barber.dao.*;
import com.app.barber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class WebSecurity {

    private VisitDao visitDao;
    private ServiceDao serviceDao;
    private WorkerDao workerDao;
    private OpenDao openDao;

    @Autowired
    public WebSecurity(VisitDao visitDao, ServiceDao serviceDao, WorkerDao workerDao, OpenDao openDao) {
        this.visitDao = visitDao;
        this.serviceDao = serviceDao;
        this.workerDao = workerDao;
        this.openDao = openDao;
    }

    public Boolean barberOwner(Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if(user.getBarber() != null){
            return user.getBarber().getId().equals(id);
        }
        return false;
    }

    public Boolean openOwner(Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return openDao.existsByIdAndBarber(id, user.getBarber());
    }

    public Boolean workerOwner(Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return workerDao.existsByIdAndBarber(id , user.getBarber());
    }

    public Boolean serviceOwner(Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return serviceDao.existsByIdAndBarber(id, user.getBarber());
    }

    public Boolean visitOwner(Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return visitDao.existsByIdAndBarber(id, user.getBarber());
    }
}
