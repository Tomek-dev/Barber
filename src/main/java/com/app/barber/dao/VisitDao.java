package com.app.barber.dao;

import com.app.barber.model.*;
import com.app.barber.other.dto.VisitOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface VisitDao extends JpaRepository<Visit, Long> {
    List<Visit> findByBarber(Barber barber);
    boolean existsByWorkerAndFinishBetweenOrBeginningBetween(Worker worker, LocalDateTime finishDate1, LocalDateTime beginningDate1, LocalDateTime finishDate2, LocalDateTime beginningDate2);
    List<Visit> findByServiceAndBeginningBetweenOrderByBeginningAsc(Service service, LocalDateTime day, LocalDateTime next);
    Boolean existsByIdAndCustomer(Long id, OAuthUser user);
    List<Visit> findByCustomerAndBeginningLessThan(OAuthUser user, LocalDateTime date);
    List<Visit> findByCustomerAndBeginningGreaterThan(OAuthUser user, LocalDateTime date);
}
