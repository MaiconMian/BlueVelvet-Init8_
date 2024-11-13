package com.bluevelvet.DTO;

import com.bluevelvet.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
@Getter
@Setter
public class ProductPhotosDTO {

    private int id;

    private Product product;

    private byte[] image;
}
