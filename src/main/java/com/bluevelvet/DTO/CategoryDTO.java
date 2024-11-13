package com.bluevelvet.DTO;

import com.bluevelvet.model.Brand;
import com.bluevelvet.model.Category;
import com.bluevelvet.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
public class CategoryDTO {

    private CategoryDTO categoryParent;

    private String categoryName;

    private byte[] image;

    private Set<BrandDTO> brands;

    private Set<ProductDTO> products;
}
