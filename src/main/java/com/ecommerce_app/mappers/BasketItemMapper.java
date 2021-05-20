package com.ecommerce_app.mappers;

import com.ecommerce_app.domain.entities.Basket;
import com.ecommerce_app.domain.entities.BasketItem;
import com.ecommerce_app.domain.entities.Product;
import com.ecommerce_app.dtos.BasketItemRequest;
import com.ecommerce_app.dtos.BasketItemResponse;

public class BasketItemMapper {

    public static BasketItemResponse mapToBasketItemResponse(BasketItem basketItem) {
        return BasketItemResponse.builder()
                .id(basketItem.getId())
                .product(basketItem.getProduct())
                .price(basketItem.getPrice())
                .basket(basketItem.getBasket())
                .build();
    }

    public static BasketItem mapRequestToEntity(BasketItemRequest basketItemRequest, Product product, Basket basket) {
        return BasketItem.builder()
                .product(product)
                .price(basketItemRequest.getPrice())
                .basket(basket)
                .build();
    }
}
