package com.ecommerce_app.services;

import com.ecommerce_app.dtos.BasketRequest;
import com.ecommerce_app.dtos.BasketResponse;

public interface BasketService {

    BasketResponse getBasket(Long id);

    BasketResponse addBasket(Long userId, BasketRequest basketRequest);
}
