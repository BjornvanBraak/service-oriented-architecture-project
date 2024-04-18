package com.example.balanceService.repository;

import java.util.List;

import com.example.balanceService.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
}