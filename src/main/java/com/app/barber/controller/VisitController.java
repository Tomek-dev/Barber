package com.app.barber.controller;

import com.app.barber.model.OAuthUser;
import com.app.barber.other.dto.AvailableVisitOutputDto;
import com.app.barber.other.dto.VisitInputDto;
import com.app.barber.other.dto.VisitOutputDto;
import com.app.barber.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PreAuthorize("hasRole('ROLE_USER') && @webSecurity.visitOwner(#id, authentication)")
    @GetMapping("/visit/{id}")
    public List<VisitOutputDto> findAllByBarber(@PathVariable Long id){
        return visitService.findAllByBarber(id);
    }

    @PreAuthorize("isAuthenticated() && hasRole('ROLE_OAUTH')")
    @PostMapping("/oauth/visit/add")
    public void add(@Valid @RequestBody VisitInputDto visit, @AuthenticationPrincipal OAuthUser user){
        visitService.add(visit, user);
    }

    @GetMapping("/visit/")
    public List<AvailableVisitOutputDto> findAllAvailable(@RequestParam Long id, @RequestParam String date){
        return visitService.findAllAvailable(id, date);
    }

    @PreAuthorize("hasRole('ROLE_OAUTH') && @webSecurity.visitCustomer(id, authentication)")
    @DeleteMapping("/oauth/visit/{id}")
    public void delete(@PathVariable Long id){
        visitService.delete(id);
    }
}
