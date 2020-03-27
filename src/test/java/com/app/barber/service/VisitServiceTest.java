package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ServiceDao;
import com.app.barber.dao.VisitDao;
import com.app.barber.dao.WorkerDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Service;
import com.app.barber.model.Visit;
import com.app.barber.model.Worker;
import com.app.barber.other.builder.BarberBuidler;
import com.app.barber.other.builder.ServiceBuilder;
import com.app.barber.other.builder.VisitBuilder;
import com.app.barber.other.builder.WorkerBuilder;
import com.app.barber.other.dto.VisitInputDto;
import com.app.barber.other.dto.VisitOutputDto;
import com.app.barber.other.exception.ServiceNotFoundException;
import com.app.barber.other.exception.VisitDateNotAvailableException;
import com.app.barber.other.exception.WorkerNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class VisitServiceTest {

    @Mock
    private VisitDao visitDao;

    @Mock
    private ServiceDao serviceDao;

    @Mock
    private WorkerDao workerDao;

    @Mock
    private BarberDao barberDao;

    @InjectMocks
    private VisitService visitService;

    @Test
    public void shouldReturnListOfVisitOutputDto(){
        //given
        Service service = ServiceBuilder.builder()
                .price(1.0)
                .name("name")
                .build();
        Visit visit = VisitBuilder.builder()
                .service(service)
                .name("name")
                .build();
        Worker worker = WorkerBuilder.builder()
                .visits(Collections.singleton(visit))
                .name("name")
                .build();
        Barber barber = BarberBuidler.buidler()
                .workers(Collections.singleton(worker))
                .build();
        visit.setWorker(worker);
        given(barberDao.findById(Mockito.any())).willReturn(java.util.Optional.ofNullable(barber));
        given(visitDao.findByWorkerIn(Mockito.any())).willReturn(Collections.singletonList(visit));

        //when
        List<VisitOutputDto> visits = visitService.findAllByBarber(4L);

        //then
        assertEquals("name", visits.get(0).getServiceName());
        assertEquals("name", visits.get(0).getName());
        assertEquals("name", visits.get(0).getWorkerName());
        assertEquals(1.0, visits.get(0).getServicePrice());
    }

    @Test
    public void shouldThrowServiceNotFoundException(){
        //given
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(ServiceNotFoundException.class, () -> visitService.add(new VisitInputDto()));
    }

    @Test
    public void shouldThrowWorkerNotFoundException(){
        //given
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(new Service()));
        given(workerDao.findById(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(WorkerNotFoundException.class, () -> visitService.add(new VisitInputDto()));
    }

    @Test
    public void shouldThrowVisitDateNotAvailable(){
        //given
        Service service = new Service();
        service.setTime(0L);
        VisitInputDto visit = new VisitInputDto();
        visit.setMonth(1);
        visit.setDay(1);
        visit.setHour(1);
        visit.setMinutes(1);
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));
        given(workerDao.findById(Mockito.any())).willReturn(Optional.of(new Worker()));
        given(visitDao.existsByWorkerAndFinishLessThanEqualOrBeginningGreaterThanEqual(
                Mockito.any(), Mockito.any(), Mockito.any())).willReturn(true);


        //then
        assertThrows(VisitDateNotAvailableException.class, () -> visitService.add(visit));
    }
}