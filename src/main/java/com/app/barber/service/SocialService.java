package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.SocialDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Social;
import com.app.barber.other.builder.SocialBuilder;
import com.app.barber.other.dto.SocialDto;
import com.app.barber.other.enums.SocialType;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.SocialNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocialService {

    private final ModelMapper mapper = new ModelMapper();

    private SocialDao socialDao;
    private BarberDao barberDao;

    @Autowired
    public SocialService(SocialDao socialDao, BarberDao barberDao) {
        this.socialDao = socialDao;
        this.barberDao = barberDao;
    }

    public void add(SocialDto socialDto, Barber barber){
        Social social = SocialBuilder.builder()
                .barber(barber)
                .type(SocialType.valueOf(socialDto.getSocialType().toUpperCase()))
                .url(socialDto.getUrl())
                .build();
        socialDao.save(social);
    }

    public List<SocialDto> getAll(Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        return barber.getSocials().stream()
                .map(social -> mapper.map(social, SocialDto.class))
                .collect(Collectors.toList());
    }

    public void edit(SocialDto socialDto, Long id){
        Optional<Social> socialOptional = socialDao.findById(id);
        Social social = socialOptional.orElseThrow(SocialNotFoundException::new);
        social.setUrl(socialDto.getUrl());
        socialDao.save(social);
    }

    public void delete(Long id){
        socialDao.deleteById(id);
    }
}
