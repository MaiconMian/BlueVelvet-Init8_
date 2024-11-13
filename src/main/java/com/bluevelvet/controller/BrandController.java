package com.bluevelvet.controller;

import com.bluevelvet.model.ApiResponse;
import com.bluevelvet.model.Category;
import com.bluevelvet.service.BrandService;
import com.bluevelvet.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bluevelvet.model.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public ResponseEntity<ApiResponse<List<Brand>>> getAllProducts(){
        List<Brand> brands = brandService.getAllBrands();
        if (brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Error", "Brands not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Success", "Brand found", brands));
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<ApiResponse<Brand>> getProductById(@PathVariable int id) {
        Optional<Brand> brand = brandService.getBrandById(id);
        if (brand.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Success", "Brands found", brand.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Error", "Brand not found", null));
        }
    }

}
