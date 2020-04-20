package com.app.barber.controller;

import com.app.barber.model.OAuthUser;
import com.app.barber.model.User;
import com.app.barber.other.dto.AvailableVisitOutputDto;
import com.app.barber.other.dto.VisitInputDto;
import com.app.barber.other.dto.VisitOutputDto;
import com.app.barber.other.exception.IncorrectParamException;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/visits")
    public List<VisitOutputDto> findAllByBarber(@AuthenticationPrincipal User user){
        return visitService.findAllByBarber(user.getBarber());
    }

    @PreAuthorize("isAuthenticated() && hasRole('ROLE_OAUTH')")
    @PostMapping("/oauth/visit/add")
    public void add(@Valid @RequestBody VisitInputDto visit, @AuthenticationPrincipal OAuthUser user){
        visitService.add(visit, user);
    }

    @GetMapping("/visit")
    public List<AvailableVisitOutputDto> findAllAvailable(@RequestParam Long id, @RequestParam String date){
        return visitService.findAllAvailable(id, date);
    }

    @PreAuthorize("hasRole('ROLE_OAUTH') && @webSecurity.visitCustomer(id, authentication)")
    @DeleteMapping("/oauth/visit/{id}")
    public void delete(@PathVariable Long id){
        visitService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_OAUTH')")
    @GetMapping("/oauth/visit/value")
    public List<VisitOutputDto> findAllByCustomerAndDate(@RequestParam String date, @RequestParam String method,@AuthenticationPrincipal OAuthUser user){
        if(method.equals("greater")){
            return visitService.findAllByCustomerAndDateGreatherThan(user, date);
        }
        if(method.equals("less")){
            return visitService.findAllByCustomerAndDateLessThan(user, date);
        }
        throw new IncorrectParamException();
    }
}
