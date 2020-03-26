package com.app.barber.controller;

import com.app.barber.other.dto.VisitOutputDto;
import com.app.barber.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    //only barber which is owner (openId)
    @GetMapping("/visit/{id}")
    public List<VisitOutputDto> findAllByBarber(@PathVariable Long id){
        return visitService.findAllByBarber(id);
    }

    //only owner of this (openId)
    @DeleteMapping("/visit/{id}")
    public void delete(@PathVariable Long id){
        visitService.delete(id);
    }
}
