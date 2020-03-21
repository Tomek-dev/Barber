package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.WorkerDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Worker;
import com.app.barber.other.builder.WorkerBuilder;
import com.app.barber.other.dto.WorkerInputDto;
import com.app.barber.other.dto.WorkerOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.WorkerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {

    private final ModelMapper mapper = new ModelMapper();

    private WorkerDao workerDao;
    private BarberDao barberDao;

    @Autowired
    public WorkerService(WorkerDao workerDao, BarberDao barberDao) {
        this.workerDao = workerDao;
        this.barberDao = barberDao;
    }

    public void add(WorkerInputDto workerDto, Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber foundBarber = barberOptional.orElseThrow(BarberNotFoundException::new);
        Worker worker = WorkerBuilder.builder()
                .name(workerDto.getName())
                .barber(foundBarber)
                .build();
        workerDao.save(worker);
    }

    public WorkerOutputDto getById(Long id){
        Optional<Worker> workerOptional = workerDao.findById(id);
        Worker worker = workerOptional.orElseThrow(WorkerNotFoundException::new);
        return mapper.map(worker, WorkerOutputDto.class);
    }

    public void delete(Long id){
        workerDao.deleteById(id);
    }
}
