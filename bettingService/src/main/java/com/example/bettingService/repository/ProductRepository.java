package com.example.bettingService.repository;

import java.util.List;

import com.example.bettingService.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
}