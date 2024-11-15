package com.bluevelvet.service;

import com.bluevelvet.DTO.ProductDTO;
import com.bluevelvet.exception.BrandNotFoundException;
import com.bluevelvet.mapper.ProductMapper;
import com.bluevelvet.model.Product;
import com.bluevelvet.model.ProductDetails;
import com.bluevelvet.model.ProductPhotos;
import com.bluevelvet.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private ProductPhotosService productPhotosService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Product saveProductWithDetails(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);

        saveProduct(product);

        brandService.getBrandById(productDTO.getBrand())
                .ifPresentOrElse(product::setBrand,
                        () -> { throw new BrandNotFoundException("Brand with ID " +
                                productDTO.getBrand() + " not found!"); }
                );

        productDTO.getCategories().forEach(categoryId -> {
            categoryService.getCategoryById(categoryId)
                    .ifPresent(product.getCategories()::add);
        });

        productDTO.getDetails().forEach(detailDTO -> {
            ProductDetails productDetails = new ProductDetails();
            productDetails.setDetailName(detailDTO.getDetailName());
            productDetails.setDetailValue(detailDTO.getDetailValue());
            product.getDetails().add(productDetails);
            productDetails.setProduct(product);
        });

        productDTO.getPhotos().forEach(photoDTO -> {
            ProductPhotos productPhoto = new ProductPhotos();
            productPhoto.setImage(photoDTO.getImage());
            product.getPhotos().add(productPhoto);
            productPhoto.setProduct(product);
        });

        saveProduct(product);

        product.getDetails().forEach(productDetailsService::saveProductDetails);
        product.getPhotos().forEach(productPhotosService::saveProductPhoto);
        return product;
    }
}
