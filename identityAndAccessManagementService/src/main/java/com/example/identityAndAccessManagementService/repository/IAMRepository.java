package com.example.identityAndAccessManagementService.repository;

import com.example.identityAndAccessManagementService.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAMRepository extends CrudRepository<Customer, Long> {

}
