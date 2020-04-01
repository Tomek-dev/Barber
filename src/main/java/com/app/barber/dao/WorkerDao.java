package com.app.barber.dao;

import com.app.barber.model.Barber;
import com.app.barber.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerDao extends JpaRepository<Worker, Long> {
    Boolean existsByIdAndBarber(Long id, Barber barber);
}
