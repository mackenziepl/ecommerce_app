package com.ecommerce_app.domain.repositories;

import com.ecommerce_app.domain.entities.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}
