package com.example.ui.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginAttemptResponse {
    private boolean successfulLogin;
    private CustomerResponse customer;
}
