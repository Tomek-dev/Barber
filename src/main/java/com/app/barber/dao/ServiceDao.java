package com.app.barber.dao;

import com.app.barber.model.Barber;
import com.app.barber.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<Service, Long> {
    Boolean existsByIdAndBarber(Long id, Barber barber);
}
