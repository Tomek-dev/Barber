package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.SocialDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Social;
import com.app.barber.other.builder.SocialBuilder;
import com.app.barber.other.dto.SocialEditDto;
import com.app.barber.other.dto.SocialInputDto;
import com.app.barber.other.dto.SocialOutputDto;
import com.app.barber.other.enums.SocialType;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SocialServiceTest {

    @Mock
    private SocialDao socialDao;

    @Mock
    private BarberDao barberDao;

    @InjectMocks
    private SocialService socialService;

    @Test
    public void shouldReturnList(){
        //given
        Barber barber = new Barber();
        Social social = SocialBuilder.builder()
                .url("url")
                .type(SocialType.FACEBOOK)
                .barber(barber)
                .build();
        barber.setSocials(Collections.singleton(social));
        given(barberDao.findById(Mockito.any())).willReturn(Optional.of(barber));

        //when
        List<SocialOutputDto> list = socialService.getAll(4L);

        //then
        assertEquals("url", list.get(0).getUrl());
        assertEquals("Facebook", list.get(0).getSocialType());
    }

    @Test
    public void shouldAdd(){
        //given
        SocialInputDto socialInputDto = new SocialInputDto();
        socialInputDto.setSocialType("facebook");
        socialInputDto.setUrl("url");

        //when
        socialService.add(socialInputDto, new Barber());

        //then
        verify(socialDao).save(any());
    }

    @Test
    public void shouldEdit(){
        //given
        Social social = new Social();
        SocialEditDto socialEditDto = new SocialEditDto();
        socialEditDto.setUrl("url");
        given(socialDao.findById(Mockito.any())).willReturn(Optional.of(social));

        //when
        socialService.edit(socialEditDto,4L);

        //then
        verify(socialDao).save(social);
        assertEquals("url", social.getUrl());
    }

}