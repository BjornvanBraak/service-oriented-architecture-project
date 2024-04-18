package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
}