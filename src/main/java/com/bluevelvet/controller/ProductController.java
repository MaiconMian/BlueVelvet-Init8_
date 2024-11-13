package com.bluevelvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import com.bluevelvet.service.*;
import com.bluevelvet.model.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<Product>>>  getAllProducts(){
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Error", "Product not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Success", "Product found", products));
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable int id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Success", "Product found", product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Error", "Product not found", null));
        }
    }
    @PostMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductById(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("Success", "Product deleted successfully", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Error", "Product not found", null));
        }
    }

}
