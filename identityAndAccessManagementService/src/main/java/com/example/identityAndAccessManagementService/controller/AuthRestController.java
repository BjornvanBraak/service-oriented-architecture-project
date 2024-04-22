package com.example.identityAndAccessManagementService.controller;

import com.example.identityAndAccessManagementService.entity.Customer;
import com.example.identityAndAccessManagementService.entity.Session;
import com.example.identityAndAccessManagementService.repository.IAMRepository;
import com.example.identityAndAccessManagementService.repository.SessionRepository;
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

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/token/{sessionToken}")
    public boolean verifySessionToken(@PathVariable String sessionToken){
        return authService.verifySessionToken(sessionToken);
    }

    /**
     * For testing
     * @return
     */
    @GetMapping("/tokens")
    public Iterable<Session> getAllTokens(){
        return sessionRepository.findAll();
    }

    @PostMapping("")
    public LoginAttemptResponse login(@RequestBody LoginAttempt loginAttempt){
        Optional<Customer> customer = authService.loginAttempt(loginAttempt.username, loginAttempt.password);
        if(customer.isEmpty()){
            return new LoginAttemptResponse(false, null, null);
        }
        String generated_session_token = "test_session_" + String.valueOf((int) Math.floor(Math.random() * 100000));
        authService.saveSessionToken(generated_session_token, customer.get().getId());
        return new LoginAttemptResponse(true, generated_session_token, customer.get());
    }

    @DeleteMapping("/logout/{customerId}")
    public void logout(@PathVariable Long customerId){
        authService.logout(customerId);
    }


    @AllArgsConstructor
    public static class LoginAttempt {
        public String username;
        public String password;
    }
}
