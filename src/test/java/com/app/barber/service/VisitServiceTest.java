package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ServiceDao;
import com.app.barber.dao.VisitDao;
import com.app.barber.dao.WorkerDao;
import com.app.barber.model.*;
import com.app.barber.other.builder.*;
import com.app.barber.other.dto.AvailableVisitOutputDto;
import com.app.barber.other.dto.VisitInputDto;
import com.app.barber.other.dto.VisitOutputDto;
import com.app.barber.other.exception.ServiceNotFoundException;
import com.app.barber.other.exception.VisitDateNotAvailableException;
import com.app.barber.other.exception.WorkerNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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

    private Barber barber;
    private Service service;
    private Visit visit;
    private Worker worker;
    private Open open;

    @Before
    public void init(){
        open = OpenBuilder.builder()
                .open(LocalTime.parse("10:00:00.0"))
                .close(LocalTime.parse("12:00:00.0"))
                .build();
        barber = BarberBuidler.buidler()
                .workers(Collections.singleton(worker))
                .open(open)
                .build();
        worker = WorkerBuilder.builder()
                .visits(Collections.singleton(visit))
                .barber(barber)
                .name("name")
                .build();
        service = ServiceBuilder.builder()
                .price(1.0)
                .name("name")
                .time(30L)
                .worker(worker)
                .build();
        visit = VisitBuilder.builder()
                .beginning(LocalDateTime.parse("2020-03-29T10:30:01.0"))
                .finish(LocalDateTime.parse("2020-03-29T11:00:00.0"))
                .service(service)
                .name("name")
                .build();
    }

    @Test
    public void shouldReturnListOfVisitOutputDto(){
        //given
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
        assertThrows(ServiceNotFoundException.class, () -> visitService.findAllAvailable(4L, "2020-03-29"));
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
        visit.setDate("2020-03-29T08:30:00.0");
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));
        given(workerDao.findById(Mockito.any())).willReturn(Optional.of(new Worker()));
        given(visitDao.existsByWorkerAndFinishBetweenOrBeginningBetween(
                Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).willReturn(true);


        //then
        assertThrows(VisitDateNotAvailableException.class, () -> visitService.add(visit));
    }

    @Test
    public void shouldReturnListOfAvailableVisitOutputDto1(){
        //given
        given(visitDao.findByServiceAndBeginningBetweenOrderByBeginningAsc(
                Mockito.any(), Mockito.any(), Mockito.any())).willReturn(Collections.singletonList(visit));
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));

        //when
        List<AvailableVisitOutputDto> visits = visitService.findAllAvailable(4L, "2020-03-29");

        //then
        assertEquals("10:00", visits.get(0).getTime().toString());
        assertEquals("11:00", visits.get(1).getTime().toString());
        assertEquals("11:15", visits.get(2).getTime().toString());
        assertEquals("11:30", visits.get(3).getTime().toString());
    }

    @Test
    public void shouldReturnListOfAvailableVisitOutputDto2(){
        //given
        Visit visit = VisitBuilder.builder()
                .beginning(LocalDateTime.parse("2020-03-29T12:00:01.0"))
                .finish(LocalDateTime.parse("2020-03-29T13:00:00.0"))
                .build();
        Open open = OpenBuilder.builder()
                .open(LocalTime.parse("10:00:00.0"))
                .close(LocalTime.parse("14:00:00.0"))
                .build();
        barber.setOpen(open);
        List<Visit> found = new LinkedList<>();
        found.add(visit);
        found.add(this.visit);
        given(visitDao.findByServiceAndBeginningBetweenOrderByBeginningAsc(
                Mockito.any(), Mockito.any(), Mockito.any())).willReturn(found);
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));

        //when
        List<AvailableVisitOutputDto> visits = visitService.findAllAvailable(4L, "2020-03-29");

        //then
        assertEquals("10:00", visits.get(0).getTime().toString());
        assertEquals("11:00", visits.get(1).getTime().toString());
        assertEquals("11:15", visits.get(2).getTime().toString());
        assertEquals("11:30", visits.get(3).getTime().toString());
        assertEquals("13:00", visits.get(4).getTime().toString());
        assertEquals("13:15", visits.get(5).getTime().toString());
        assertEquals("13:30", visits.get(6).getTime().toString());
    }
}