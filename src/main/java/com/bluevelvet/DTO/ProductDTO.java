package com.bluevelvet.DTO;

import com.bluevelvet.model.Brand;
import com.bluevelvet.model.Category;
import com.bluevelvet.model.ProductDetails;
import com.bluevelvet.model.ProductPhotos;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductDTO {

    private Brand brand;

    private String name;

    private byte[] image;

    private String shortDescription;

    private String longDescription;

    private float price;

    private float discount;

    private Boolean status;

    private Boolean hasStock;

    private float widht;

    private float lenght;

    private float height;

    private float cost;

    private LocalDateTime creationTime;

    private LocalDateTime updateTime;

    private List<ProductDetailsDTO> details;

    private List<ProductPhotosDTO> photos;

    private Set<Category> categories;

}



