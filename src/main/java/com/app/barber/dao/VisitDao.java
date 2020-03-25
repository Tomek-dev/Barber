package com.app.barber.dao;

import com.app.barber.model.Visit;
import com.app.barber.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VisitDao extends JpaRepository<Visit, Long> {
    List<Visit> findByWorkerIn(Collection<Worker> workers);
}
