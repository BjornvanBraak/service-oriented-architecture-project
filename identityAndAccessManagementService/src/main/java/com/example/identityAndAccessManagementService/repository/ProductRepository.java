package com.example.identityAndAccessManagementService.repository;

import java.util.List;

import com.example.identityAndAccessManagementService.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
}