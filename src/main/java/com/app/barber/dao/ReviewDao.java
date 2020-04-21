package com.app.barber.dao;

import com.app.barber.model.OAuthUser;
import com.app.barber.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {
    List<Review> findByBarberId(Long id);
    Page<Review> findByBarberId(Long id, Pageable pageable);
    Boolean existsByIdAndOwner(Long id, OAuthUser user);
}
