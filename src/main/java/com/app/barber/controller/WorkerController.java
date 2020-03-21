package com.app.barber.controller;

import com.app.barber.other.dto.WorkerInputDto;
import com.app.barber.other.dto.WorkerOutputDto;
import com.app.barber.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WorkerController {

    private WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping("/worker/add/{id}")
    public void add(@RequestBody WorkerInputDto workerDto, @PathVariable Long id){
        workerService.add(workerDto, id);
    }

    @DeleteMapping("/worker/{id}")
    public void delete(@PathVariable Long id){
        workerService.delete(id);
    }

    @GetMapping("/worker/{id}")
    public WorkerOutputDto get(@PathVariable Long id){
        return workerService.getById(id);
    }
}
