package com.ecommerce_app.dtos;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Value
@Builder
public class ProductResponse {

    Long id;
    String name;
    BigDecimal price;
    String productImage;
    String details;
    List<String> attributes;
    BigInteger productEvaluation;
    String unitStock;
}