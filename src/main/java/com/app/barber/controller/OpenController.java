package com.app.barber.controller;

import com.app.barber.other.dto.OpenDto;
import com.app.barber.service.OpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequestMapping("/api")
public class OpenController {

    private OpenService openService;

    @Autowired
    public OpenController(OpenService openService) {
        this.openService = openService;
    }

    @PostMapping("/open/add/{id}")
    public void add(@PathVariable Long id, @RequestBody OpenDto open){
        openService.add(id, open);
    }

    @GetMapping("/open/{id}")
    public OpenDto get(@PathVariable Long id){
        return openService.get(id);
    }

    @DeleteMapping("/open/{id}")
    public void delete(@PathVariable Long id){
        openService.delete(id);
    }
}
