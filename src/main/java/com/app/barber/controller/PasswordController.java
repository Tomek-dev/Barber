package com.app.barber.controller;

import com.app.barber.other.dto.ForgotInputDto;
import com.app.barber.other.dto.PasswordDto;
import com.app.barber.other.dto.ResetInputDto;
import com.app.barber.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    private PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/change")
    public ResponseEntity<?> change(@Valid @RequestBody PasswordDto password, Authentication authentication){
        passwordService.reset(password, authentication);
        return ResponseEntity.ok("Password change successfully");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@Valid @RequestBody ResetInputDto reset){
        passwordService.reset(reset);
        return ResponseEntity.ok("Reset password successfully");
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestBody ForgotInputDto forgot){
        passwordService.reset(forgot);
        return ResponseEntity.ok("Send email");
    }
}

