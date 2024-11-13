package com.bluevelvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import com.bluevelvet.service.*;
import com.bluevelvet.model.*;
import com.bluevelvet.DTO.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductPhotosService productPhotosService;
    @Autowired
    private ProductDetailsService productDetailsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

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
            return ResponseEntity.ok(new ApiResponse<>("Success", "Product found", product.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Error", "Product not found", null));
        }
    }
    @PostMapping("/deleteproduct/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductById(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("Success", "Product deleted successfully", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Error", "Product not found", null));
        }
    }
    @PostMapping("/addproduct")
    public ResponseEntity<ApiResponse<Product>> addProduct(@RequestBody ProductDTO productDTO) {

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setImage(productDTO.getImage());
        product.setShortDescription(productDTO.getShortDescription());
        product.setLongDescription(productDTO.getLongDescription());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setStatus(productDTO.getStatus());
        product.setHasStock(productDTO.getHasStock());
        product.setWidht(productDTO.getWidht());
        product.setHeight(productDTO.getHeight());
        product.setCost(productDTO.getCost());
        product.setCreationTime(productDTO.getCreationTime());
        product.setUpdateTime(productDTO.getUpdateTime());

        productDTO.getDetails().forEach(productDetailsDTO -> {
            ProductDetails productDetails = new ProductDetails();
            productDetails.setDetailName(productDetailsDTO.getDetailName());
            productDetails.setDetailValue(productDetailsDTO.getDetailValue());
            productDetails.setProduct(product);
            product.getDetails().add(productDetails);
            productDetailsService.saveProductDetails(productDetails);
        });

        productDTO.getPhotos().forEach(productPhotosDTO -> {
            ProductPhotos productPhotos = new ProductPhotos();
            productPhotos.setImage(productPhotosDTO.getImage());
            productPhotos.setProduct(product);
            product.getPhotos().add(productPhotos);
            productPhotosService.saveProductPhoto(productPhotos);
        });

        productDTO.getCategories().forEach(categoryDTO -> {
            categoryService.getCategoryById(categoryDTO.getId()).ifPresent(category -> product.getCategories().add(category));
        });

        brandService.getBrandById(productDTO.getBrand().getId()).ifPresent(brand -> product.setBrand(brand));

        productService.saveProduct(product);
        return ResponseEntity.ok(new ApiResponse<>("Success", "Product added successfully", product));

    }

}
