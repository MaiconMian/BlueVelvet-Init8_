package com.bluevelvet.service;


import com.bluevelvet.model.ProductPhotos;
import com.bluevelvet.repository.ProductPhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductPhotosService {

    @Autowired
    private ProductPhotosRepository productPhotosRepository;

    public ProductPhotos saveProductPhoto(ProductPhotos productPhotos) {
        return productPhotosRepository.save(productPhotos);
    }

    public ProductPhotos updateProductPhoto(int id, ProductPhotos productPhotos) {
        if (productPhotosRepository.existsById(id)) {
            productPhotos.setId(id);
            return productPhotosRepository.save(productPhotos);
        }
        return null;
    }

    public void deleteProductPhoto(int id) {
        if (productPhotosRepository.existsById(id)) {
            productPhotosRepository.deleteById(id);
        }
    }

    public List<ProductPhotos> getAllProductPhotos() {
        return productPhotosRepository.findAll();
    }

    public Optional<ProductPhotos> getProductPhotoById(int id) {
        return productPhotosRepository.findById(id);
    }

    public List<ProductPhotos> getProductPhotosByProductId(int productId) {
        return productPhotosRepository.findAll().stream()
                .filter(photo -> photo.getProduct().getId() == productId)
                .toList();
    }

}
