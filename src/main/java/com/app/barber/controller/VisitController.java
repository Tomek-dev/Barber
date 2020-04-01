package com.app.barber.controller;

import com.app.barber.other.dto.AvailableVisitOutputDto;
import com.app.barber.other.dto.VisitInputDto;
import com.app.barber.other.dto.VisitOutputDto;
import com.app.barber.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    //only barber which is owner
    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.visitOwner(#id, authentication)")
    @GetMapping("/visit/{id}")
    public List<VisitOutputDto> findAllByBarber(@PathVariable Long id){
        return visitService.findAllByBarber(id);
    }

    //authenticated (openId)
    @PostMapping("/visit/add")
    public void add(@Valid @RequestBody VisitInputDto visit){
        visitService.add(visit);
    }

    @GetMapping("/visit/")
    public List<AvailableVisitOutputDto> findAllAvailable(@RequestParam Long id, @RequestParam String date){
        return visitService.findAllAvailable(id, date);
    }

    //only owner of this (openId)
    @DeleteMapping("/visit/{id}")
    public void delete(@PathVariable Long id){
        visitService.delete(id);
    }
}
