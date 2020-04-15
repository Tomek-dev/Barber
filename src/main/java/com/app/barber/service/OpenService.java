package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.OpenDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Open;
import com.app.barber.other.builder.OpenBuilder;
import com.app.barber.other.dto.OpenInputDto;
import com.app.barber.other.dto.OpenOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.OpenNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OpenService {

    private final ModelMapper mapper = new ModelMapper();

    private OpenDao openDao;
    private BarberDao barberDao;

    @Autowired
    public OpenService(OpenDao openDao, BarberDao barberDao) {
        this.openDao = openDao;
        this.barberDao = barberDao;
    }

    public void changeDay(Long id, OpenInputDto openInputDto){
        Optional<Open> openOptional = openDao.findByBarberIdAndDay(id, DayOfWeek.valueOf(openInputDto.getDay().toUpperCase()));
        Open open = openOptional.orElseThrow(OpenNotFoundException::new);
        open.setOpen(LocalTime.parse(openInputDto.getOpen()));
        open.setClose(LocalTime.parse(openInputDto.getClose()));
        openDao.save(open);
    }

    public void setWeek(Long id, List<OpenInputDto> openInputDto){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        openInputDto.forEach(day -> {
            Open open = OpenBuilder.builder()
                    .open(LocalTime.parse(day.getOpen()))
                    .close(LocalTime.parse(day.getClose()))
                    .barber(barber)
                    .day(DayOfWeek.valueOf(day.getDay().toUpperCase()))
                    .build();
            openDao.save(open);
        });
    }

    public OpenOutputDto get(Long id){
        Optional<Open> openOptional = openDao.findById(id);
        Open open = openOptional.orElseThrow(OpenNotFoundException::new);
        return mapper.map(open, OpenOutputDto.class);
    }

    public List<OpenOutputDto> getWeek(Long id){
        List<Open> opens = openDao.findByBarberId(id);
        return opens.stream()
                .sorted(Comparator.comparingInt(o -> o.getDay().getValue()))
                .map(open -> mapper.map(open, OpenOutputDto.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        openDao.deleteById(id);
    }
}
