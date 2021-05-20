package com.ecommerce_app.services.impl;

import com.ecommerce_app.controllers.exceptions.ResourceNotFoundException;
import com.ecommerce_app.domain.entities.Basket;
import com.ecommerce_app.domain.entities.BasketItem;
import com.ecommerce_app.domain.entities.Product;
import com.ecommerce_app.domain.repositories.BasketItemRepository;
import com.ecommerce_app.domain.repositories.BasketRepository;
import com.ecommerce_app.domain.repositories.ProductRepository;
import com.ecommerce_app.dtos.BasketItemRequest;
import com.ecommerce_app.dtos.BasketItemResponse;
import com.ecommerce_app.dtos.BasketRequest;
import com.ecommerce_app.mappers.BasketItemMapper;
import com.ecommerce_app.services.BasketItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemRepository basketItemRepository;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public BasketItemResponse addBasketItem(BasketItemRequest basketItemRequest, Long productId, Long basketId) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found with id: " + basketId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        BasketItem basketItem = BasketItemMapper.mapRequestToEntity(basketItemRequest, product, basket);
        basketItemRepository.save(basketItem);
        return BasketItemMapper.mapToBasketItemResponse(basketItem);
    }

    @Transactional
    @Override
    public void deleteBasketItem(Long basketId, Long basketItemsId) {
        BasketItem basketItem = basketItemRepository.findById(basketItemsId)
                .orElseThrow(() -> new ResourceNotFoundException("BasketItem with id: " + basketItemsId + " not found"));
        if(basketItem.getBasket().getId().equals(basketId)) {
            basketItemRepository.delete(basketItem);
        }
    }
}
