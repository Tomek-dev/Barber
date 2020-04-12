package com.app.barber.dao;

import com.app.barber.model.Barber;
import com.app.barber.model.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialDao extends JpaRepository<Social, Long> {
    Boolean existsByIdAndBarber(Long id, Barber barber);
}
