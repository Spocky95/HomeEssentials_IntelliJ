package com.HomeEssentials.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

// Klasa ProductCategory reprezentuje encję kategorii produktu w bazie danych.
@Entity
@Table(name="product_category")
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Reszta pól encji ProductCategory.
    @Column(name = "categoryName")
    private String categoryName;

    // Relacja jeden do wielu z encją Product.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;
}