package com.ecommerce_app.services;

import com.ecommerce_app.dtos.BasketItemRequest;
import com.ecommerce_app.dtos.BasketItemResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface BasketItemService {

    BasketItemResponse addBasketItem(BasketItemRequest basketItemRequest, Long productId, Long basketId);

    void deleteBasketItem(Long basketId, @PathVariable Long basketItemsId);
}
