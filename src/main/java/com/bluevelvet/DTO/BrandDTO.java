package com.bluevelvet.DTO;

import com.bluevelvet.model.Category;
import com.bluevelvet.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BrandDTO {

    private String categoryName;

    private byte[] image;

    private List<ProductDTO> products;

    private Set<CategoryDTO> category;
}
