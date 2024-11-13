package com.bluevelvet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import java.util.*;
import java.io.*;

@Entity
@Getter
@Setter
@Table (name="bv_product_photos")
public class ProductPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private Product product;

    @Column(name = "product_ph_content")
    private byte[] image;
}
