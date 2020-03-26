package com.app.barber.controller;

import com.app.barber.model.User;
import com.app.barber.other.dto.WorkerInputDto;
import com.app.barber.other.dto.WorkerOutputDto;
import com.app.barber.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public void add(@RequestBody WorkerInputDto workerDto, @AuthenticationPrincipal User user){
        workerService.add(workerDto, user.getBarber().getId());
    }

    //only owner of this
    @DeleteMapping("/worker/{id}")
    public void delete(@PathVariable Long id){
        workerService.delete(id);
    }

    @GetMapping("/worker/{id}")
    public WorkerOutputDto get(@PathVariable Long id){
        return workerService.getById(id);
    }
}
