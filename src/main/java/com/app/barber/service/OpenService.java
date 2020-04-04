package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.OpenDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Open;
import com.app.barber.other.builder.OpenBuilder;
import com.app.barber.other.dto.OpenDto;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.OpenNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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

    public void changeDay(Long id, OpenDto openDto){
        Optional<Open> openOptional = openDao.findByBarberIdAndDay(id, DayOfWeek.valueOf(openDto.getDay().toUpperCase()));
        Open open = openOptional.orElseThrow(OpenNotFoundException::new);
        open.setOpen(LocalTime.parse(openDto.getOpen()));
        open.setClose(LocalTime.parse(openDto.getClose()));
        openDao.save(open);
    }

    public void setWeek(Long id, List<OpenDto> openDto){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        openDto.forEach(day -> {
            Open open = OpenBuilder.builder()
                    .open(LocalTime.parse(day.getOpen()))
                    .close(LocalTime.parse(day.getClose()))
                    .barber(barber)
                    .day(DayOfWeek.valueOf(day.getDay().toUpperCase()))
                    .build();
            openDao.save(open);
        });
    }

    public OpenDto get(Long id){
        Optional<Open> openOptional = openDao.findById(id);
        Open open = openOptional.orElseThrow(OpenNotFoundException::new);
        return mapper.map(open, OpenDto.class);
    }

    public List<OpenDto> getWeek(Long id){
        List<Open> opens = openDao.findByBarberId(id);
        return opens.stream()
                .map(open -> mapper.map(open, OpenDto.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        openDao.deleteById(id);
    }
}
