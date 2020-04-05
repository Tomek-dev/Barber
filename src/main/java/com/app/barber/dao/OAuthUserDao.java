package com.app.barber.dao;

import com.app.barber.model.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthUserDao extends JpaRepository<OAuthUser, Long> {
    Optional<OAuthUser> findByEmail(String email);
}
