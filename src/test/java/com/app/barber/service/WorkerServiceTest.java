package com.app.barber.service;

import com.app.barber.dao.WorkerDao;
import com.app.barber.model.Worker;
import com.app.barber.other.builder.WorkerBuilder;
import com.app.barber.other.dto.WorkerOutputDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class WorkerServiceTest {

    @Mock
    private WorkerDao workerDao;

    @InjectMocks
    private WorkerService workerService;

    @Test
    public void shouldReturnWorkerOutputDto(){
        //given
        Worker worker = WorkerBuilder.builder()
                .name("name")
                .build();
        given(workerDao.findById(Mockito.any())).willReturn(java.util.Optional.ofNullable(worker));

        //when
        WorkerOutputDto output = workerService.getById(4L);

        //then
        assertEquals("name", output.getName());
    }
}