package com.app.barber.controller;

import com.app.barber.model.User;
import com.app.barber.other.dto.ForgotInputDto;
import com.app.barber.other.dto.PasswordDto;
import com.app.barber.other.dto.ResetInputDto;
import com.app.barber.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/password")
@PreAuthorize("permitAll()")
public class PasswordController {

    private PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/change")
    public void change(@Valid @RequestBody PasswordDto password, @AuthenticationPrincipal User user){
        passwordService.change(password, user);
    }

    @PutMapping("/reset/value")
    public void reset(@Valid @RequestBody ResetInputDto reset, @RequestParam String token){
        passwordService.reset(reset, token);
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@Valid @RequestBody ForgotInputDto forgot){
        passwordService.createReset(forgot);
        return ResponseEntity.ok("Successfully create reset token");
    }
}

