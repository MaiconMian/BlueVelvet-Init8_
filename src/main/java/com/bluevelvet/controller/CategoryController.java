package com.bluevelvet.controller;

import com.bluevelvet.model.ApiResponse;
import com.bluevelvet.service.CategoryService;
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
    public ResponseEntity<ApiResponse<Object>> getAllProducts(){
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", "No categories registered"));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", categories));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<Object>> getProductById(@PathVariable int id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (!category.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", "Category not found"));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", category.get()));
    }

}
