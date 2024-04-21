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
    public LoginAttemptResponse login(@RequestBody LoginAttempt loginAttempt){
        Optional<Customer> customer = authService.loginAttempt(loginAttempt.username, loginAttempt.password);
        if(customer.isEmpty()){
            return new LoginAttemptResponse(false, null, null);
        }
        String generated_session_token = "test";
        return new LoginAttemptResponse(true, generated_session_token, customer);
    }


    @AllArgsConstructor
    public static class LoginAttempt {
        public String username;
        public String password;
    }
}
