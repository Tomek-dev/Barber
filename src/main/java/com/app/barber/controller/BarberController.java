package com.app.barber.controller;

import com.app.barber.dao.BarberDao;
import com.app.barber.other.dto.BarberInputDto;
import com.app.barber.other.dto.BarberOutputDto;
import com.app.barber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BarberController {

    private BarberService barberService;

    @Autowired
    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @PostMapping("/barber/add/{id}")
    public void add(@RequestBody BarberInputDto barberDto, @PathVariable Long id){
        barberService.add(barberDto, id);
    }

    @GetMapping("/barber/{id}")
    public BarberOutputDto get(@PathVariable Long id){
        return barberService.getById(id);
    }

    @DeleteMapping("/barber/{id}")
    public void delete(@PathVariable Long id){
        barberService.delete(id);
    }
}
