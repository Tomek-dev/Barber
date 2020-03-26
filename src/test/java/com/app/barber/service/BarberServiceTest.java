package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.UserDao;
import com.app.barber.model.Barber;
import com.app.barber.model.User;
import com.app.barber.other.builder.BarberBuidler;
import com.app.barber.other.dto.BarberInputDto;
import com.app.barber.other.dto.BarberOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BarberServiceTest {

    @Mock
    private BarberDao barberDao;

    @Mock
    private UserDao userDao;

    @Mock
    private GeocodeService geocodeService;

    @InjectMocks
    private BarberService barberService;

    @Test
    public void  shouldThrowBarberNotFoundException(){
        //given
        given(barberDao.findById(Mockito.any(Long.class))).willReturn(Optional.empty());

        //then
        assertThrows(BarberNotFoundException.class ,() -> barberService.getById(4L));
    }

    @Test
    public void  shouldThrowUsernameNotFoundException(){
        //given
        given(userDao.findById(Mockito.any(Long.class))).willReturn(Optional.empty());

        //then
        assertThrows(UsernameNotFoundException.class ,() -> barberService.add(new BarberInputDto(), 4L));
    }

    @Test
    public void shouldDeleteBarber(){
        //when
        barberService.delete(4L);

        //then
        verify(barberDao).deleteById(4L);
    }

    @Test
    public void shouldReturnBarberOutputDto(){
        //given
        Barber barber = BarberBuidler.buidler()
                .name("name")
                .address("address")
                .city("city")
                .local("1")
                .build();
        given(barberDao.findById(Mockito.any(Long.class))).willReturn(Optional.ofNullable(barber));

        //when
        BarberOutputDto output = barberService.getById(4L);

        //then
        assertEquals("name", output.getName());
        assertEquals("address", output.getAddress());
        assertEquals("city", output.getCity());
        assertEquals("1", output.getLocal());
    }
}