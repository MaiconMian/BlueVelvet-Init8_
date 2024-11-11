package com.bluevelvet.service;

import com.bluevelvet.model.ProductDetails;
import com.bluevelvet.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bluevelvet.model.*;
import com.bluevelvet.repository.*;
import java.util.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailsService {

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    public List<ProductDetails> getAllProductDetails() {
        return productDetailsRepository.findAll();
    }

    public Optional<ProductDetails> getProductDetailsById(int id) {
        return productDetailsRepository.findById(id);
    }

    public ProductDetails saveProductDetails(ProductDetails productDetails) {
        return productDetailsRepository.save(productDetails);
    }
    public ProductDetails updateProductDetails(int id, ProductDetails productDetails) {
        if (productDetailsRepository.existsById(id)) {
            productDetails.setId(id);
            return productDetailsRepository.save(productDetails);
        }
        return null;
    }

    public void deleteProductDetails(int id) {
        productDetailsRepository.deleteById(id);
    }


}
