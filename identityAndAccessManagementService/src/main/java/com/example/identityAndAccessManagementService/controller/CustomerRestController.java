package com.example.identityAndAccessManagementService.controller;

import com.example.identityAndAccessManagementService.entity.Customer;
import com.example.identityAndAccessManagementService.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public Iterable<Customer> fetchCustomers(){
        return customerService.findAll();
    }

    @PostMapping("")
    public Customer saveCustomer(@Valid @RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping("/{id}")
    public Optional<Customer> fetchCustomer(@PathVariable("id") Long id){
        return customerService.findById(id);
    }
}
