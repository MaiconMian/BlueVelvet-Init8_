package com.bluevelvet.controller;

import com.bluevelvet.model.ApiResponse;
import com.bluevelvet.service.BrandService;
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
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public ResponseEntity<ApiResponse<Object>> getAllBrands(){
        List<Brand> brands = brandService.getAllBrands();
        if (brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", "No brands registered"));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", brands));
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<ApiResponse<Object>> getBrandById(@PathVariable int id) {
        Optional<Brand> brand = brandService.getBrandById(id);
        if (!brand.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", "Brand not found"));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", brand.get()));
    }

}
