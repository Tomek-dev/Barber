package com.app.barber.controller;

import com.app.barber.model.User;
import com.app.barber.other.dto.AvailabilityDto;
import com.app.barber.other.dto.WorkerInputDto;
import com.app.barber.other.dto.WorkerOutputDto;
import com.app.barber.other.exception.IncorrectParamException;
import com.app.barber.other.validation.ValidList;
import com.app.barber.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkerController {

    private WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/worker/add")
    public void add(@Valid @RequestBody WorkerInputDto workerDto, @AuthenticationPrincipal User user){
        workerService.add(workerDto, user.getBarber().getId());
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.workerOwner(#id, authentication)")
    @PutMapping("/worker/{id}")
    public void edit(@Valid @RequestBody WorkerInputDto workerDto, @PathVariable Long id){
        workerService.edit(workerDto, id);
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.workerOwner(#workerId, authentication)")
    @PostMapping("/worker/{workerId}/add/{serviceId}")
    public void addTo(@PathVariable Long serviceId, @PathVariable Long workerId){
        workerService.addTo(serviceId, workerId);
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.workerOwner(#workerId, authentication)")
    @PostMapping("/worker/{workerId}/remove/{serviceId}")
    public void removeFrom(@PathVariable Long serviceId, @PathVariable Long workerId){
        workerService.removeFrom(serviceId, workerId);
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.workerOwner(#id, authentication)")
    @DeleteMapping("/worker/{id}")
    public void delete(@PathVariable Long id){
        workerService.delete(id);
    }

    @GetMapping("/worker/{id}")
    public WorkerOutputDto get(@PathVariable Long id){
        return workerService.getById(id);
    }

    @GetMapping("/worker/available/{name}")
    public AvailabilityDto availableName(@PathVariable String name, @AuthenticationPrincipal User user){
        return new AvailabilityDto(workerService.availableName(name, user.getBarber()));
    }

    @GetMapping("/worker/value")
    public List<WorkerOutputDto> getAll(@RequestParam(required = false) Long barber, @RequestParam(required = false) Long service){
        if((barber == null && service == null)
                || (barber != null && service != null)) {
            throw new IncorrectParamException();
        }
        return (barber == null ? workerService.getAllByServiceId(service): workerService.getByBarberId(barber));
    }
}
