package com.example.identityAndAccessManagementService.controller;

import com.example.identityAndAccessManagementService.entity.Customer;
import com.example.identityAndAccessManagementService.services.AuthService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    @Autowired
    private AuthService authService;

    @PostMapping("")
    public Optional<Customer> login(@RequestBody LoginAttempt loginAttempt){
        return authService.loginAttempt(loginAttempt.username, loginAttempt.password);
    }


    @AllArgsConstructor
    public static class LoginAttempt {
        public String username;
        public String password;
    }
}
