package com.HomeEssentials.dao;

import com.HomeEssentials.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

// Interfejs ProductRepository rozszerza JpaRepository, co umożliwia korzystanie z gotowych metod do operacji CRUD.

@RepositoryRestResource
@CrossOrigin("https://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long>{



    Page<Product>  findByCategoryId(@Param("id") Long id, Pageable pageable);
    //SELECT * FROM Product p WHERE p.category.id = :id

    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
    //SELECT * FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%')

    Page<Product> findByActive(@Param("active") boolean active, Pageable pageable);
    //SELECT * FROM Product p WHERE p.active = :active
    //https://localhost:4090/api/products/search/findByActive?active=false
}