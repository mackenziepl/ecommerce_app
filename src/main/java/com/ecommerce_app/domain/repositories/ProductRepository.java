package com.ecommerce_app.domain.repositories;

import com.ecommerce_app.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);
}
