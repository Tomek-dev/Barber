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
import com.app.barber.other.dto.VisitOutputDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class VisitServiceTest {

    @Mock
    private VisitDao visitDao;

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
}