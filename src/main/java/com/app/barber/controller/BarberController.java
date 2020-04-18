package com.app.barber.controller;

import com.app.barber.model.Barber;
import com.app.barber.model.User;
import com.app.barber.other.dto.BarberInputDto;
import com.app.barber.other.dto.BarberOutputDto;
import com.app.barber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public void add(@Valid @RequestBody BarberInputDto barberDto, @AuthenticationPrincipal User user){
        barberService.add(barberDto, user.getId());
    }

    @GetMapping("/barber/{id}")
    public BarberOutputDto get(@PathVariable Long id){
        return barberService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/barber")
    public void edit(@RequestBody @Valid BarberInputDto barberDto, @AuthenticationPrincipal User user){
        barberService.edit(barberDto, user.getBarber());
    }

    @GetMapping("/barbers")
    public List<BarberOutputDto> findTop(){
        return barberService.getTop10();
    }

    @PreAuthorize("isAuthenticated() && hasRole('ROLE_USER')")
    @GetMapping("/barber")
    public BarberOutputDto getByUser(@AuthenticationPrincipal User user){
        return barberService.getByUser(user);
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.barberOwner(#id, authentication)")
    @DeleteMapping("/barber/{id}")
    public void delete(@PathVariable Long id){
        barberService.delete(id);
    }
}
