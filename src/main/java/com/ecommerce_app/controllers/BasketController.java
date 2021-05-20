package com.ecommerce_app.controllers;

import com.ecommerce_app.dtos.BasketRequest;
import com.ecommerce_app.dtos.BasketResponse;
import com.ecommerce_app.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/baskets/{id}")
    public ResponseEntity<BasketResponse> getBasket(@PathVariable Long id) {
        BasketResponse response = basketService.getBasket(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/baskets")
    public ResponseEntity<BasketResponse> addBasket(@PathVariable Long userId, @Valid @RequestBody BasketRequest basketRequest) {
        BasketResponse basketResponse = basketService.addBasket(userId, basketRequest);
        return new ResponseEntity<>(basketResponse, HttpStatus.CREATED);
    }
}
