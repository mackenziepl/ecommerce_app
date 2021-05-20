package com.ecommerce_app.mappers;

import com.ecommerce_app.domain.entities.Basket;
import com.ecommerce_app.domain.entities.User;
import com.ecommerce_app.dtos.BasketRequest;
import com.ecommerce_app.dtos.BasketResponse;

public class BasketMapper {

    public static BasketResponse mapToBasketResponse(Basket basket) {
        return BasketResponse.builder()
                .id(basket.getId())
                .user(basket.getUser())
                .basketItems(basket.getBasketItems())
                .totalPrice(basket.getTotalPrice())
                .discountCode(basket.getDiscountCode())
                .build();
    }

    public static Basket mapRequestToEntity(BasketRequest basketRequest, User user) {
        return Basket.builder()
                .user(user)
                .basketItems(basketRequest.getBasketItems())
                .totalPrice(basketRequest.getTotalPrice())
                .discountCode(basketRequest.getDiscountCode())
                .build();
    }
}
