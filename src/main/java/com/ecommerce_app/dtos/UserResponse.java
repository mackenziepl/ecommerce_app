package com.ecommerce_app.dtos;

import com.ecommerce_app.domain.entities.Basket;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponse {

    Long id;
    String firstName;
    String lastName;
    String deliveryAddress;
    String email;
    Basket basket;
}
