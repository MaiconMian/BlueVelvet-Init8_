package com.bluevelvet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import java.util.*;
import java.io.*;

@Getter
@Setter
@Entity
@Table (name="bv_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_parent_id", insertable = false, updatable = false)
    private Category categoryParent;

    @Column(name = "category_name", length=100)
    private String categoryName;

    @Column(name = "category_ph_content")
    private byte[] image;

    @ManyToMany(mappedBy = "category")
    private Set<Brand> brands;

    @ManyToMany
    @JoinTable(
            name = "bv_product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

}
