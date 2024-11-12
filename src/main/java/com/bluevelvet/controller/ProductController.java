package com.bluevelvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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
}
