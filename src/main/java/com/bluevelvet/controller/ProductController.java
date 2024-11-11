package com.bluevelvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.bluevelvet.service.*;
import com.bluevelvet.model.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/api/v1/products")
    public List<Product> getAllProducts(){
        productService.createTestProducts(); // Apenas para testes
        return productService.getAllProducts();
    }
    @GetMapping("/api/v1/product/{id}")
    public Optional<Product> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
    @PostMapping("/api/v1/product/{id}")
    public boolean deleteProductById(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
