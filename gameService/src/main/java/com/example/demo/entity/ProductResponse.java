package com.example.gameService.entity;

import java.util.List;

/**
 * @author Amol Limaye
 **/
public class ProductResponse {
    private List<Product> products;

    public ProductResponse(List<Product> products){
        this.products = products;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}


