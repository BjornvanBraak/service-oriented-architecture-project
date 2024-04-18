package com.example.gameService.repository;

import java.util.List;

import com.example.gameService.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
}