package com.HomeEssentials.dao;

import com.HomeEssentials.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

// Interfejs ProductRepository rozszerza JpaRepository, co umożliwia korzystanie z gotowych metod do operacji CRUD.
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long>{



    Page<Product>  findByCategoryId(@Param("id") Long id, Pageable pageable);
    //SELECT * FROM Product p WHERE p.category.id = :id
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
    //SELECT * FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%')





}