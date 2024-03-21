package com.HomeEssentials.dao;

import com.HomeEssentials.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Interfejs ProductCategoryRepository rozszerza JpaRepository, co umożliwia korzystanie z gotowych metod do operacji CRUD.
// Adnotacja @RepositoryRestResource umożliwia eksponowanie tego repozytorium jako zasobu REST.
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}