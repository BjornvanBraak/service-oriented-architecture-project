package com.example.identityAndAccessManagementService.controller;

import com.example.identityAndAccessManagementService.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class LoginAttemptResponse {
    private boolean successfulLogin;
    private String sessionToken;
    private Customer customer;
}
