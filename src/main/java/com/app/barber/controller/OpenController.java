package com.app.barber.controller;

import com.app.barber.other.dto.OpenInputDto;
import com.app.barber.other.dto.OpenOutputDto;
import com.app.barber.other.validation.ValidList;
import com.app.barber.service.OpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OpenController {

    private OpenService openService;

    @Autowired
    public OpenController(OpenService openService) {
        this.openService = openService;
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.openOwner(#id, authentication)")
    @PutMapping("/open/{id}")
    public void changeDay(@PathVariable Long id, @Valid @RequestBody OpenInputDto open){
        openService.changeDay(id, open);
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.barberOwner(#id, authentication)")
    @PostMapping("/open/add/{id}")
    public void setWeek(@PathVariable Long id, @RequestBody @Valid ValidList<OpenInputDto> open){
        openService.setWeek(id, open);
    }

    @GetMapping("/open/week/{id}")
    public List<OpenOutputDto> getWeek(@PathVariable Long id){
        return openService.getWeek(id);
    }

    @GetMapping("/open/{id}")
    public OpenOutputDto get(@PathVariable Long id){
        return openService.get(id);
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.openOwner(#id, authentication)")
    @DeleteMapping("/open/{id}")
    public void delete(@PathVariable Long id){
        openService.delete(id);
    }
}
