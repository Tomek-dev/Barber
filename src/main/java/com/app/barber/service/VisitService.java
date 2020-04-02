package com.app.barber.service;

import com.app.barber.dao.*;
import com.app.barber.model.Barber;
import com.app.barber.model.Open;
import com.app.barber.model.Visit;
import com.app.barber.model.Worker;
import com.app.barber.other.builder.VisitBuilder;
import com.app.barber.other.dto.AvailableVisitOutputDto;
import com.app.barber.other.dto.VisitInputDto;
import com.app.barber.other.dto.VisitOutputDto;
import com.app.barber.other.exception.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
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
    private OpenDao openDao;

    @Autowired
    public VisitService(ServiceDao serviceDao, VisitDao visitDao, WorkerDao workerDao, BarberDao barberDao, OpenDao openDao) {
        this.serviceDao = serviceDao;
        this.visitDao = visitDao;
        this.workerDao = workerDao;
        this.barberDao = barberDao;
        this.openDao = openDao;
    }

    public List<VisitOutputDto> findAllByBarber(Long id){
        Optional<Barber> barberOptional = barberDao.findById(id);
        Barber barber = barberOptional.orElseThrow(BarberNotFoundException::new);
        List<Visit> visits = visitDao.findByBarber(barber);
        return visits.stream()
                .map(visit -> mapper.map(visit, VisitOutputDto.class))
                .collect(Collectors.toList());
    }

    public List<AvailableVisitOutputDto> findAllAvailable(Long id, String date){
        Optional<com.app.barber.model.Service> serviceOptional = serviceDao.findById(id);
        com.app.barber.model.Service service = serviceOptional.orElseThrow(ServiceNotFoundException::new);
        Optional<Open> openOptional = openDao.findByBarberAndDay(service.getBarber(), DayOfWeek.from(LocalDate.parse(date)));
        Open open = openOptional.orElseThrow(OpenNotFoundException::new);
        List<LocalTime> times = new LinkedList<>();
        LocalTime time = open.getOpen();
        LocalDate day = LocalDate.parse(date);
        List<Visit> visits = visitDao.findByServiceAndBeginningBetweenOrderByBeginningAsc(service, day.atStartOfDay(), day.plusDays(1).atStartOfDay());
        while (time.compareTo(open.getClose()) < 0){
            times.add(time);
            time = time.plusMinutes(15);
        }
        for (Visit visit : visits) {
            times = times.stream()
                    .filter(value -> !(value.compareTo(visit.getBeginning().toLocalTime().minusSeconds(1)) >= 0
                            && value.compareTo(visit.getFinish().toLocalTime()) < 0)
                            && !(value.plusMinutes(service.getTime()).compareTo(visit.getBeginning().toLocalTime()) >= 0
                            && value.plusMinutes(service.getTime()).compareTo(visit.getFinish().toLocalTime()) < 0)
                            && value.plusMinutes(service.getTime()).compareTo(open.getClose()) <= 0)
                    .collect(Collectors.toList());
        }
        return times.stream()
                .map(AvailableVisitOutputDto::new)
                .collect(Collectors.toList());
    }

    public void add(VisitInputDto visitDto){
        Optional<com.app.barber.model.Service> serviceOptional = serviceDao.findById(visitDto.getService());
        com.app.barber.model.Service service = serviceOptional.orElseThrow(ServiceNotFoundException::new);
        Optional<Worker> workerOptional = workerDao.findById(visitDto.getWorker());
        Worker worker = workerOptional.orElseThrow(WorkerNotFoundException::new);
        //TODO check if worker has this service
        Barber barber = service.getBarber();
        Optional<Open> openOptional = openDao.findByBarberAndDay(barber, DayOfWeek.from(LocalDateTime.parse(visitDto.getDate())));
        Open open = openOptional.orElseThrow(OpenNotFoundException::new);
        LocalDateTime beginning = LocalDateTime.parse(visitDto.getDate());
        LocalDateTime finish = beginning.plusMinutes(service.getTime());
        beginning = beginning.plusSeconds(1);
        if(visitDao.existsByWorkerAndFinishBetweenOrBeginningBetween(
                worker, beginning, finish, beginning, finish)
                || finish.toLocalTime().compareTo(open.getClose().plusSeconds(1)) > 0
                || beginning.toLocalTime().compareTo(open.getOpen()) < 0){
            throw new VisitDateNotAvailableException();
        }
        Visit visit = VisitBuilder.builder()
                .name(visitDto.getName())
                .worker(worker)
                .service(service)
                .barber(barber)
                .beginning(beginning)
                .finish(finish)
                .build();
        visitDao.save(visit);
    }

    public void delete(Long id){
        visitDao.deleteById(id);
    }
}
