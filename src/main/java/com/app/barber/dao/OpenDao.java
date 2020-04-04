package com.app.barber.dao;

import com.app.barber.model.Barber;
import com.app.barber.model.Open;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public interface OpenDao extends JpaRepository<Open, Long> {
    Optional<Open> findByBarberIdAndDay(Long id, DayOfWeek day);
    Optional<Open> findByBarberAndDay(Barber barber, DayOfWeek day);
    Boolean existsByIdAndBarber(Long id, Barber barber);
    List<Open> findByBarberId(Long id);
}
