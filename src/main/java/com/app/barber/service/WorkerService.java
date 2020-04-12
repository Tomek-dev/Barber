package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ServiceDao;
import com.app.barber.dao.WorkerDao;
import com.app.barber.model.Barber;
import com.app.barber.model.User;
import com.app.barber.model.Worker;
import com.app.barber.other.builder.WorkerBuilder;
import com.app.barber.other.dto.WorkerInputDto;
import com.app.barber.other.dto.WorkerOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.BelongException;
import com.app.barber.other.exception.ServiceNotFoundException;
import com.app.barber.other.exception.WorkerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    private final ModelMapper mapper = new ModelMapper();

    private WorkerDao workerDao;
    private BarberDao barberDao;
    private ServiceDao serviceDao;

    @Autowired
    public WorkerService(WorkerDao workerDao, BarberDao barberDao, ServiceDao serviceDao) {
        this.workerDao = workerDao;
        this.barberDao = barberDao;
        this.serviceDao = serviceDao;
    }

    public void add(WorkerInputDto workerDto, Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        Worker worker = WorkerBuilder.builder()
                .name(workerDto.getName())
                .barber(barber)
                .build();
        workerDao.save(worker);
    }

    public void addTo(Long serviceId, Long workerId){
        Optional<Worker> workerOptional = workerDao.findById(workerId);
        Worker worker = workerOptional.orElseThrow(WorkerNotFoundException::new);
        Optional<com.app.barber.model.Service> serviceOptional = serviceDao.findById(serviceId);
        com.app.barber.model.Service service = serviceOptional.orElseThrow(ServiceNotFoundException::new);
        if(!worker.getBarber().getId().equals(service.getBarber().getId())){
            throw new BelongException();
        }
        worker.addService(service);
        workerDao.save(worker);
    }

    public void removeFrom(Long serviceId, Long workerId){
        Optional<Worker> workerOptional = workerDao.findById(workerId);
        Worker worker = workerOptional.orElseThrow(WorkerNotFoundException::new);
        Optional<com.app.barber.model.Service> serviceOptional = serviceDao.findById(serviceId);
        com.app.barber.model.Service service = serviceOptional.orElseThrow(ServiceNotFoundException::new);
        if(!(worker.getBarber().getId().equals(service.getBarber().getId()))
                || !(worker.getServices().contains(service))){
            throw new BelongException();
        }
        worker.removeService(service);
        workerDao.save(worker);
    }

    public List<WorkerOutputDto> getByBarberId(Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        return barber.getWorkers().stream()
                .map(worker -> mapper.map(worker, WorkerOutputDto.class))
                .collect(Collectors.toList());
    }

    public WorkerOutputDto getById(Long id){
        Optional<Worker> workerOptional = workerDao.findById(id);
        Worker worker = workerOptional.orElseThrow(WorkerNotFoundException::new);
        return mapper.map(worker, WorkerOutputDto.class);
    }

    public Boolean availableName(String name, Barber barber){
        return !workerDao.existsByBarberAndName(barber, name);
    }

    public void delete(Long id){
        Optional<Worker> workerOptional = workerDao.findById(id);
        Worker worker = workerOptional.orElseThrow(WorkerNotFoundException::new);
        worker.getServices().removeIf(service -> service.getWorkers().contains(worker));
        workerDao.delete(worker);
    }

    public void edit(WorkerInputDto workerDto, Long id) {
        Optional<Worker> workerOptional = workerDao.findById(id);
        Worker worker = workerOptional.orElseThrow(WorkerNotFoundException::new);
        worker.setName(workerDto.getName());
        workerDao.save(worker);
    }
}
