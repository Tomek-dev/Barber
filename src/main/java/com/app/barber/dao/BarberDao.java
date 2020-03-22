package com.app.barber.dao;

import com.app.barber.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberDao extends JpaRepository<Barber, Long>, JpaSpecificationExecutor<Barber> {
}
