package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.model.Barber;
import com.app.barber.other.builder.BarberBuidler;
import com.app.barber.other.dto.BarberOutputDto;
import com.app.barber.other.specification.SearchSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

    @Mock
    private BarberDao barberDao;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void shouldReturnListOfBarberOutputDto(){
        //given
        Barber barber = BarberBuidler.buidler()
                .address("address")
                .name("name")
                .city("city")
                .local("1")
                .build();
        given(barberDao.findAll(Mockito.any(Specification.class))).willReturn(Collections.singletonList(barber));

        //when
        List<BarberOutputDto> barbers = searchService.search(new SearchSpecification("", ""));

        //then
        assertEquals("name", barbers.get(0).getName());
        assertEquals("1", barbers.get(0).getLocal());
        assertEquals("address", barbers.get(0).getAddress());
        assertEquals("city", barbers.get(0).getCity());
    }
}