package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ServiceDao;
import com.app.barber.dao.WorkerDao;
import com.app.barber.model.Service;
import com.app.barber.model.Worker;
import com.app.barber.other.builder.ServiceBuilder;
import com.app.barber.other.builder.WorkerBuilder;
import com.app.barber.other.dto.ServiceInputDto;
import com.app.barber.other.dto.ServiceOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
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
public class ServiceServiceTest {

    @Mock
    private ServiceDao serviceDao;

    @Mock
    private WorkerDao workerDao;

    @Mock
    private BarberDao barberDao;

    @InjectMocks
    private ServiceService service;

    @Test
    public void shouldThrowWorkerNotFoundException(){
        //given
        given(workerDao.findById(Mockito.any())).willReturn(Optional.empty());
        given(barberDao.findById(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(BarberNotFoundException.class, () -> service.add(new ServiceInputDto(), 4L));
        assertThrows(WorkerNotFoundException.class, () -> service.getAllByWorkerId(4L));
    }

    @Test
    public void shouldReturnListOfServiceOutputDto(){
        //given
        Service service = ServiceBuilder.builder()
                .name("name")
                .price(1.0)
                .description("description")
                .build();
        Worker worker = WorkerBuilder.builder()
                .name("name")
                .services(Collections.singleton(service))
                .build();
        service.setWorkers(Collections.singleton(worker));
        given(workerDao.findById(Mockito.any())).willReturn(Optional.ofNullable(worker));

        //when
        List<ServiceOutputDto> services = this.service.getAllByWorkerId(4L);

        //then
        assertEquals("name", services.get(0).getName());
        assertTrue(services.get(0).getWorkers().contains("name"));
        assertEquals(1.0, services.get(0).getPrice());
        assertEquals("description", services.get(0).getDescription());
    }
}