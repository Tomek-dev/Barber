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

import java.time.LocalTime;
import java.util.Optional;

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

    public void add(Long id, OpenDto openDto){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        Open open = OpenBuilder.builder()
                .open(LocalTime.parse(openDto.getOpen()))
                .close(LocalTime.parse(openDto.getClose()))
                .barber(barber)
                .build();
        openDao.save(open);
    }

    //update

    public OpenDto get(Long id){
        Optional<Open> openOptional = openDao.findById(id);
        Open open = openOptional.orElseThrow(OpenNotFoundException::new);
        return mapper.map(open, OpenDto.class);
    }

    public void delete(Long id){
        openDao.deleteById(id);
    }
}
