package com.app.barber.dao;

import com.app.barber.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {
    List<Image> findByBarberId(Long id);
}
