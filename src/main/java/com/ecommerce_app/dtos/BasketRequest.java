package com.ecommerce_app.dtos;

import com.ecommerce_app.domain.entities.BasketItem;
import com.ecommerce_app.domain.entities.User;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class BasketRequest {

    Long id;
    User user;
    List<BasketItem> basketItems;
    BigDecimal totalPrice;
    String discountCode;
}
