package com.ecommerce_app.domain.repositories;

import com.ecommerce_app.domain.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
