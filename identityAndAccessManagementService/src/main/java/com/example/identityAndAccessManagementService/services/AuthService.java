package com.example.identityAndAccessManagementService.services;

import com.example.identityAndAccessManagementService.entity.Customer;
import com.example.identityAndAccessManagementService.repository.IAMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private IAMRepository iamRepository;

    public Optional<Customer> loginAttempt(String username, String password){
        return iamRepository.findByNameAndPassword(username, password);
    }
}
