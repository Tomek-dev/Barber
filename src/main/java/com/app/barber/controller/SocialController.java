package com.app.barber.controller;

import com.app.barber.model.User;
import com.app.barber.other.dto.SocialDto;
import com.app.barber.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SocialController {

    private SocialService socialService;

    @Autowired
    public SocialController(SocialService socialService) {
        this.socialService = socialService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/social/add")
    public void add(@Valid @RequestBody SocialDto socialDto, @AuthenticationPrincipal User user){
        socialService.add(socialDto, user.getBarber());
    }

    @GetMapping("/social/{id}")
    public List<SocialDto> getAll(@PathVariable Long id){
        return socialService.getAll(id);
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.socialOwner(#id, authentication)")
    @PutMapping("/social/{id}")
    public void edit(@RequestBody @Valid SocialDto socialDto, @PathVariable Long id){
        socialService.edit(socialDto, id);
    }

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.socialOwner(#id, authentication)")
    @DeleteMapping("/social/{id}")
    public void delete(@PathVariable Long id){
        socialService.delete(id);
    }
}
