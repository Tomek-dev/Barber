package com.app.barber.controller;

import com.app.barber.model.User;
import com.app.barber.other.dto.ServiceInputDto;
import com.app.barber.other.dto.ServiceOutputDto;
import com.app.barber.other.exception.IncorrectParamException;
import com.app.barber.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {

    private ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/service/add")
    public void add(@Valid @RequestBody ServiceInputDto serviceDto, @AuthenticationPrincipal User user){
        serviceService.add(serviceDto, user.getId());
    }

    @GetMapping("/service/value")
    public List<ServiceOutputDto> findAllByWorker(@RequestParam(required = false) Long barber,
                                                  @RequestParam(required = false) Long worker){
        if((barber == null && worker == null)
        || (barber != null && worker != null)) {
            throw new IncorrectParamException();
        }
        return (barber == null ? serviceService.getAllByWorkerId(barber): serviceService.getAllByBarberId(barber));
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.serviceOwner(#id, authentication)")
    @DeleteMapping("/service/{id}")
    public void delete(@PathVariable Long id){
        serviceService.delete(id);
    }
}
