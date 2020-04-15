package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.OpenDao;
import com.app.barber.model.Open;
import com.app.barber.other.dto.OpenInputDto;
import com.app.barber.other.dto.OpenOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.OpenNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.util.*;

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
        OpenInputDto open = new OpenInputDto();
        open.setDay("friday");
        given(openDao.findByBarberIdAndDay(Mockito.any(), Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(OpenNotFoundException.class, () -> openService.changeDay(4L, open));
    }

    @Test
    public void shouldThrowBarberNotFoundException(){
        //given
        given(barberDao.findById(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(BarberNotFoundException.class, () -> openService.setWeek(4L, Collections.singletonList(new OpenInputDto())));
    }

    @Test
    public void shouldReturnListOfOpeningHours(){
        //given
        List<Open> opens = new LinkedList<>();
        Open friday = new Open();
        friday.setDay(DayOfWeek.FRIDAY);
        opens.add(friday);
        Open monday = new Open();
        monday.setDay(DayOfWeek.MONDAY);
        opens.add(monday);
        Open thursday = new Open();
        thursday.setDay(DayOfWeek.THURSDAY);
        opens.add(thursday);
        Open wednesday = new Open();
        wednesday.setDay(DayOfWeek.WEDNESDAY);
        opens.add(wednesday);
        Open saturday = new Open();
        saturday.setDay(DayOfWeek.SATURDAY);
        opens.add(saturday);
        Open sunday = new Open();
        sunday.setDay(DayOfWeek.SUNDAY);
        opens.add(sunday);
        Open tuesday = new Open();
        tuesday.setDay(DayOfWeek.TUESDAY);
        opens.add(tuesday);
        given(openDao.findByBarberId(Mockito.any())).willReturn(opens);

        //when
        List<OpenOutputDto> dto = openService.getWeek(4L);

        //then
        assertEquals(7, dto.size());
        assertEquals("Monday", dto.get(0).getDay());
        assertEquals("Tuesday", dto.get(1).getDay());
        assertEquals("Wednesday", dto.get(2).getDay());
        assertEquals("Thursday", dto.get(3).getDay());
        assertEquals("Friday", dto.get(4).getDay());
        assertEquals("Saturday", dto.get(5).getDay());
        assertEquals("Sunday", dto.get(6).getDay());
    }
}