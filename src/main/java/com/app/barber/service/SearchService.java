package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.model.Barber;
import com.app.barber.other.dto.BarberOutputDto;
import com.app.barber.other.specification.SearchSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final ModelMapper mapper = new ModelMapper();

    private BarberDao barberDao;

    @Autowired
    public SearchService(BarberDao barberDao) {
        this.barberDao = barberDao;
    }

    public List<BarberOutputDto> search(SearchSpecification specification){
        List<Barber> barbers = barberDao.findAll(specification);
        return barbers.stream()
                .map(barber -> mapper.map(barber, BarberOutputDto.class))
                .collect(Collectors.toList());
    }
}
