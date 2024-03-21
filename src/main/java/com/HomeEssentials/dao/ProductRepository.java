package com.HomeEssentials.dao;

import com.HomeEssentials.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
