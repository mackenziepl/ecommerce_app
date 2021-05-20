package com.ecommerce_app.dtos;

import com.ecommerce_app.domain.entities.Basket;
import com.ecommerce_app.domain.entities.Product;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class BasketItemRequest {

    Long id;
    Product product;
    BigDecimal price;
    Basket basket;
}
