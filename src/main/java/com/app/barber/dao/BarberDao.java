package com.app.barber.dao;

import com.app.barber.model.Barber;
import com.app.barber.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarberDao extends JpaRepository<Barber, Long>, JpaSpecificationExecutor<Barber> {
    Optional<Barber> findByUser(User user);
    List<Barber> findTop10ByOrderByReviewsDesc();
}
