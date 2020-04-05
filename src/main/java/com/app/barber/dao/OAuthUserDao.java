package com.app.barber.dao;

import com.app.barber.model.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthUserDao extends JpaRepository<OAuthUser, Long> {
    Optional<OAuthUser> findByEmail(String email);
}
