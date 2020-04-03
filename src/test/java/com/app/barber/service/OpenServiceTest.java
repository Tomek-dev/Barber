package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.OpenDao;
import com.app.barber.other.dto.OpenDto;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.OpenNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OpenServiceTest {

    @Mock
    private OpenDao openDao;

    @Mock
    private BarberDao barberDao;

    @InjectMocks
    private OpenService openService;

    @Test
    public void shouldThrowOpenNotFoundException1(){
        //given
        given(openDao.findById(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(OpenNotFoundException.class, () -> openService.get(4L));
    }

    @Test
    public void shouldThrowOpenNotFoundException2(){
        //given
        OpenDto open = new OpenDto();
        open.setDay("monday");
        given(openDao.findByBarberIdAndDay(Mockito.any(), Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(OpenNotFoundException.class, () -> openService.changeDay(4L, open));
    }

    @Test
    public void shouldThrowBarberNotFoundException(){
        //given
        given(barberDao.findById(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(BarberNotFoundException.class, () -> openService.setWeek(4L, Collections.singletonList(new OpenDto())));
    }
}