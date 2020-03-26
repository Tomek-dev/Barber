package com.app.barber.controller;

import com.app.barber.dao.BarberDao;
import com.app.barber.model.User;
import com.app.barber.other.dto.BarberInputDto;
import com.app.barber.other.dto.BarberOutputDto;
import com.app.barber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class BarberController {

    private BarberService barberService;

    @Autowired
    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/barber/add")
    public void add(@RequestBody BarberInputDto barberDto, @AuthenticationPrincipal User user){
        barberService.add(barberDto, user.getId());
    }

    @GetMapping("/barber/{id}")
    public BarberOutputDto get(@PathVariable Long id){
        return barberService.getById(id);
    }

    @PreAuthorize("isAuthenticated() && #id == authentication.getPrincipal().getBarber().getId()")
    @DeleteMapping("/barber/{id}")
    public void delete(@PathVariable Long id){
        barberService.delete(id);
    }
}
