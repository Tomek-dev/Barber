package com.app.barber.controller;

import com.app.barber.model.User;
import com.app.barber.other.dto.ServiceInputDto;
import com.app.barber.other.dto.ServiceOutputDto;
import com.app.barber.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    //only barber/user
    @PostMapping("/service/add/{id}")
    public void add(@Valid @RequestBody ServiceInputDto serviceDto, @PathVariable Long id){
        serviceService.add(serviceDto, id);
    }

    @GetMapping("/service/{id}")
    public List<ServiceOutputDto> findAllByWorker(@PathVariable Long id){
        return serviceService.getAllByWorkerId(id);
    }

    //only owner of this
    @DeleteMapping("/service/{id}")
    public void delete(@PathVariable Long id){
        serviceService.delete(id);
    }
}
