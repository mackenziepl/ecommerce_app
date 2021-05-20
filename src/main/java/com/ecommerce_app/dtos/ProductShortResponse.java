package com.ecommerce_app.dtos;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ProductShortResponse {

    Long id;
    String name;
    BigDecimal price;
    String productImage;
}
