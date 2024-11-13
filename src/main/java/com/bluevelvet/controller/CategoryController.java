package com.bluevelvet.controller;

import com.bluevelvet.model.ApiResponse;
import com.bluevelvet.model.Product;
import com.bluevelvet.service.CategoryService;
import com.bluevelvet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bluevelvet.model.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<Category>>> getAllProducts(){
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Error", "Categorys not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Success", "Product found", categories));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<Category>> getProductById(@PathVariable int id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Success", "Product found", category.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Error", "Product not found", null));
        }
    }

}
