package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ServiceDao;
import com.app.barber.dao.VisitDao;
import com.app.barber.dao.WorkerDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Visit;
import com.app.barber.other.dto.VisitOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private final ModelMapper mapper = new ModelMapper();

    private ServiceDao serviceDao;
    private VisitDao visitDao;
    private WorkerDao workerDao;
    private BarberDao barberDao;

    @Autowired
    public VisitService(ServiceDao serviceDao, VisitDao visitDao, WorkerDao workerDao, BarberDao barberDao) {
        this.serviceDao = serviceDao;
        this.visitDao = visitDao;
        this.workerDao = workerDao;
        this.barberDao = barberDao;
    }

    public List<VisitOutputDto> findAllByBarber(Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        List<Visit> visits = visitDao.findByWorkerIn(barber.getWorkers());
        return visits.stream()
                .map(visit -> mapper.map(visit, VisitOutputDto.class))
                .collect(Collectors.toList());
    }

    public void add(Long id){
        //TODO
    }

    public void delete(Long id){
        visitDao.deleteById(id);
    }
}
