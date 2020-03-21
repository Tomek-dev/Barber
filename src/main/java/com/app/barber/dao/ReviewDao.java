package com.app.barber.dao;

import com.app.barber.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {
    List<Review> findByIdOrderByDateDesc(Long id);
}
