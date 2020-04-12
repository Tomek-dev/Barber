package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.UserDao;
import com.app.barber.model.Barber;
import com.app.barber.model.User;
import com.app.barber.other.builder.BarberBuidler;
import com.app.barber.other.dto.BarberInputDto;
import com.app.barber.other.dto.BarberOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BarberService {

    private final ModelMapper mapper = new ModelMapper();

    private BarberDao barberDao;
    private UserDao userDao;
    private GeocodeService geocodeService;

    @Autowired
    public BarberService(BarberDao barberDao, UserDao userDao, GeocodeService geocodeService) {
        this.barberDao = barberDao;
        this.userDao = userDao;
        this.geocodeService = geocodeService;
    }

    public void add(BarberInputDto barberDto, Long id){
        Optional<User> userOptional =  userDao.findById(id);
        User foundUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //Double[] coordinates = geocodeService.geocoder(barberDto.getAddress(), barberDto.getCity(), barberDto.getLocal());
        Barber barber = BarberBuidler.buidler()
                .name(barberDto.getName())
                .city(barberDto.getCity())
                .local(barberDto.getLocal())
                .address(barberDto.getAddress())
                //.latitude(coordinates[0])
                //.longitude(coordinates[1])
                .user(foundUser)
                .build();
        barberDao.save(barber);
    }

    public BarberOutputDto getById(Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber foundBarber = barberOptional.orElseThrow(BarberNotFoundException::new);
        return mapper.map(foundBarber, BarberOutputDto.class);
    }

    public BarberOutputDto getByUser(User user){
        Optional<Barber> barberOptional = barberDao.findByUser(user);
        Barber foundBarber = barberOptional.orElseThrow(BarberNotFoundException::new);
        return mapper.map(foundBarber, BarberOutputDto.class);
    }

    public void delete(Long id){
        barberDao.deleteById(id);
    }
}
