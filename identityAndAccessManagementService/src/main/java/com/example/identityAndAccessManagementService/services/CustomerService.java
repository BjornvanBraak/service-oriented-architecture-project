package com.example.identityAndAccessManagementService.services;

import com.example.identityAndAccessManagementService.entity.Customer;
import com.example.identityAndAccessManagementService.repository.IAMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements BasicServiceInterface<Customer, Long> {
    @Autowired private IAMRepository iamRepository;
    @Override
    public Customer save(Customer customer) {
        return iamRepository.save(customer);
    }

    @Override
    public Iterable<Customer> findAll() {
        return iamRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return iamRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return iamRepository.existsById(id);
    }

    @Override
    public Iterable<Customer> findAllById(Iterable<Long> ids) {
        return iamRepository.findAllById(ids);
    }
}
