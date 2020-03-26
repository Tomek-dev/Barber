package com.app.barber.controller;

import com.app.barber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated() && #id == authentication.getPrincipal().getId()")
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
