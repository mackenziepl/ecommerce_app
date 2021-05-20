package com.ecommerce_app.services.impl;

import com.ecommerce_app.controllers.exceptions.ResourceNotFoundException;
import com.ecommerce_app.domain.entities.Basket;
import com.ecommerce_app.domain.entities.User;
import com.ecommerce_app.domain.repositories.BasketRepository;
import com.ecommerce_app.domain.repositories.UserRepository;
import com.ecommerce_app.dtos.BasketRequest;
import com.ecommerce_app.dtos.BasketResponse;
import com.ecommerce_app.mappers.BasketMapper;
import com.ecommerce_app.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;

    @Override
    public BasketResponse getBasket(Long id) {
        return basketRepository.findById(id)
                .map(BasketMapper::mapToBasketResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found with id: " + id));
    }

    @Transactional
    @Override
    public BasketResponse addBasket(Long userId, BasketRequest basketRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));
        Basket basket = BasketMapper.mapRequestToEntity(basketRequest, user);
        basketRepository.save(basket);
        return BasketMapper.mapToBasketResponse(basket);
    }
}