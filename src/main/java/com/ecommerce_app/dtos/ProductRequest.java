package com.ecommerce_app.dtos;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Value
@Builder
public class ProductRequest {

    Long id;
    @Size(max = 30)
    @NotBlank
    String name;
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    BigDecimal price;
    String productImage;
    @Size(max = 50)
    @NotBlank
    String details;
    List<String> attributes;
    @DecimalMin(value = "0")
    @DecimalMax(value = "5")
    BigInteger productEvaluation;
    @NotEmpty
    String unitStock;
}
