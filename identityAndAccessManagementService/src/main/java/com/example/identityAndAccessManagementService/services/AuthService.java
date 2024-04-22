package com.example.identityAndAccessManagementService.services;

import com.example.identityAndAccessManagementService.entity.Customer;
import com.example.identityAndAccessManagementService.entity.Session;
import com.example.identityAndAccessManagementService.repository.IAMRepository;
import com.example.identityAndAccessManagementService.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private IAMRepository iamRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public Optional<Customer> loginAttempt(String username, String password){
        return iamRepository.findByNameAndPassword(username, password);
    }

    public void saveSessionToken(String sessionToken, Long customerId){
        Session session = new Session(sessionToken, customerId);
        sessionRepository.save(session);
    }

    public void logout(Long customerId){
        sessionRepository.deleteAllByCustomerId(customerId);
    }

    public boolean verifySessionToken(String sessionToken){
        Optional<Session> session = sessionRepository.findById(sessionToken);
        return session.isPresent();
    }
}
