package com.HomeEssentials.dao;

import com.HomeEssentials.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// Interfejs ProductRepository rozszerza JpaRepository, co umożliwia korzystanie z gotowych metod do operacji CRUD.
public interface ProductRepository extends JpaRepository<Product, Long>{
}