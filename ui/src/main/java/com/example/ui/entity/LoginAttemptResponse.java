package com.example.ui.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginAttemptResponse {
    private boolean successfulLogin;
    private CustomerResponse customer;
}
