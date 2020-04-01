package com.app.barber.service;

import com.app.barber.dao.BarberDao;
import com.app.barber.dao.ServiceDao;
import com.app.barber.dao.WorkerDao;
import com.app.barber.model.Barber;
import com.app.barber.model.Worker;
import com.app.barber.other.builder.ServiceBuilder;
import com.app.barber.other.dto.ServiceInputDto;
import com.app.barber.other.dto.ServiceOutputDto;
import com.app.barber.other.exception.BarberNotFoundException;
import com.app.barber.other.exception.BelongException;
import com.app.barber.other.exception.ServiceNotFoundException;
import com.app.barber.other.exception.WorkerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    private final ModelMapper mapper = new ModelMapper();

    private ServiceDao serviceDao;
    private WorkerDao workerDao;
    private BarberDao barberDao;

    @Autowired
    public ServiceService(ServiceDao serviceDao, WorkerDao workerDao, BarberDao barberDao) {
        this.serviceDao = serviceDao;
        this.workerDao = workerDao;
        this.barberDao = barberDao;
    }

    public void add(ServiceInputDto serviceDto, Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        com.app.barber.model.Service service = ServiceBuilder.builder()
                .name(serviceDto.getName())
                .price(serviceDto.getPrice())
                .description(serviceDto.getDescription())
                .time(serviceDto.getTime())
                .barber(barber)
                .build();
        serviceDao.save(service);
    }

    public List<ServiceOutputDto> getAllByWorkerId(Long id){
        Optional<Worker> workerOptional = workerDao.findById(id);
        Worker worker = workerOptional.orElseThrow(WorkerNotFoundException::new);
        Set<com.app.barber.model.Service> services = worker.getServices();
        return services.stream()
                .map(service -> mapper.map(service, ServiceOutputDto.class))
                .collect(Collectors.toList());
    }

    public List<ServiceOutputDto> getAllByBarberId(Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        Set<com.app.barber.model.Service> services = barber.getServices();
        return services.stream()
                .map(service -> mapper.map(service, ServiceOutputDto.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        Optional<com.app.barber.model.Service> serviceOptional = serviceDao.findById(id);
        com.app.barber.model.Service service = serviceOptional.orElseThrow(ServiceNotFoundException::new);
        service.getWorkers().forEach(worker -> worker.removeService(service));
        serviceDao.delete(service);
    }
}
