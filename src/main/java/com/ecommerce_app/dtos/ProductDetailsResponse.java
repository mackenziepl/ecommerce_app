package com.ecommerce_app.dtos;

import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;
import java.util.List;

@Builder
@Value
public class ProductDetailsResponse {

    Long id;
    String details;
    List<String> attributes;
    BigInteger productEvaluation;

}