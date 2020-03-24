package com.app.barber.dao;

import com.app.barber.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenDao extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(UUID token);
    @Transactional
    void deleteByDateLessThanEqual(LocalDateTime date);
}
