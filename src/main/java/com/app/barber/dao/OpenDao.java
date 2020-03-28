package com.app.barber.dao;

import com.app.barber.model.Open;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenDao extends JpaRepository<Open, Long> {
}
