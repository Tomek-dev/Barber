package com.app.barber.service;

import com.app.barber.dao.ServiceDao;
import com.app.barber.dao.WorkerDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Service;
import com.app.barber.model.Worker;
import com.app.barber.other.builder.BarberBuidler;
import com.app.barber.other.builder.ServiceBuilder;
import com.app.barber.other.builder.WorkerBuilder;
import com.app.barber.other.dto.WorkerOutputDto;
import com.app.barber.other.exception.BelongException;
import org.junit.Before;
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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WorkerServiceTest {

    @Mock
    private WorkerDao workerDao;

    @Mock
    private ServiceDao serviceDao;

    @InjectMocks
    private WorkerService workerService;

    private Barber barber;
    private Worker worker;
    private Service service;

    @Before
    public void init(){
        barber = new Barber();
        barber.setId(4L);
        worker = WorkerBuilder.builder()
                .name("name")
                .url("url")
                .barber(barber)
                .build();
        worker.setId(4L);
        service = ServiceBuilder.builder()
                .barber(barber)
                .build();
    }

    @Test
    public void shouldReturnWorkerOutputDto(){
        //given
        given(workerDao.findById(Mockito.any())).willReturn(java.util.Optional.ofNullable(worker));

        //when
        WorkerOutputDto output = workerService.getById(4L);

        //then
        assertEquals("name", output.getName());
        assertEquals(4, output.getId());
        assertEquals("url", output.getImageUrl());
    }

    @Test
    public void shouldAddTo(){
        //given
        given(workerDao.findById(Mockito.any())).willReturn(Optional.of(worker));
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));

        //when
        workerService.addTo(4L, 4L);

        //then
        verify(workerDao).save(worker);

        assertTrue(worker.getServices().contains(service));
        assertTrue(service.getWorkers().contains(worker));
    }

    @Test
    public void shouldRemoveFrom(){
        //given
        worker.addService(service);
        given(workerDao.findById(Mockito.any())).willReturn(Optional.of(worker));
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));

        //when
        workerService.removeFrom(4L, 4L);

        //then
        verify(workerDao).save(worker);

        assertFalse(worker.getServices().contains(service));
        assertFalse(service.getWorkers().contains(worker));
    }

    @Test
    public void shouldThrowBelongException1(){
        //given
        given(workerDao.findById(Mockito.any())).willReturn(Optional.of(worker));
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));

        //when
        assertThrows(BelongException.class, () -> workerService.removeFrom(4L, 4L));
    }

    @Test
    public void shouldThrowBelongException2(){
        //given
        Barber serviceBarber = new Barber();
        serviceBarber.setId(1L);
        service.setBarber(serviceBarber);
        worker.addService(service);
        given(workerDao.findById(Mockito.any())).willReturn(Optional.of(worker));
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));

        //when
        assertThrows(BelongException.class, () -> workerService.removeFrom(4L, 4L));
    }

    @Test
    public void shouldThrowBelongException3(){
        //given
        Barber serviceBarber = new Barber();
        serviceBarber.setId(1L);
        service.setBarber(serviceBarber);
        worker.addService(service);
        given(workerDao.findById(Mockito.any())).willReturn(Optional.of(worker));
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));

        //when
        assertThrows(BelongException.class, () -> workerService.addTo(4L, 4L));
    }

    @Test
    public void shouldDelete(){
        //given
        given(workerDao.findById(Mockito.any())).willReturn(Optional.of(worker));

        //when
        workerService.delete(4L);

        //then
        verify(workerDao).delete(worker);

        assertFalse(worker.getServices().contains(service));
        assertFalse(service.getWorkers().contains(worker));
    }

    @Test
    public void shouldListReturnWorkerOutputDto(){
        //given
        worker.addService(service);
        given(serviceDao.findById(Mockito.any())).willReturn(Optional.of(service));

        //when
        List<WorkerOutputDto> workers = workerService.getAllByServiceId(4L);

        //then
        assertEquals("name", workers.get(0).getName());
        assertEquals(4, workers.get(0).getId());
        assertEquals("url", workers.get(0).getImageUrl());
    }
}