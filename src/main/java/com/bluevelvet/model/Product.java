package com.bluevelvet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import java.util.*;
import java.io.*;

@Entity
@Setter
@Getter
@Table(name = "bv_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_brand",  insertable = false, updatable = false)
    private Brand brand;

    @Column(name = "product_name", length = 100)
    private String name;

    @Column(name = "product_main_photo")
    private byte[] image;

    @Column(name = "product_short_desc", length = 100)
    private String shortDescription;

    @Column(name = "product_long_desc", length = 500)
    private String longDescription;

    @Column(name = "product_price")
    private float price;

    @Column(name = "product_discount")
    private float discount;

    @Column(name = "products_status", columnDefinition = "TINYINT")
    private Boolean status;

    @Column(name = "products_has_stock", columnDefinition = "TINYINT")
    private Boolean hasStock;

    @Column(name = "product_widht")
    private float widht;

    @Column(name = "product_lenght")
    private float lenght;

    @Column(name = "product_height")
    private float height;

    @Column(name = "product_cost")
    private float cost;

    @Column(name = "product_creation_time")
    private LocalDateTime creationTime;

    @Column(name = "product_update_time")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetails> details;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductPhotos> photos;

    @ManyToMany(mappedBy = "products")
    private Set<Category> categories;

}
