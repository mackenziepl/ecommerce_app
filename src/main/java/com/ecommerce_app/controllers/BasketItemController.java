package com.ecommerce_app.controllers;

import com.ecommerce_app.dtos.BasketItemRequest;
import com.ecommerce_app.dtos.BasketItemResponse;
import com.ecommerce_app.services.BasketItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BasketItemController {

    private final BasketItemService basketItemService;

    @PostMapping("/baskets/{basketId}/basketItems")
    public ResponseEntity<BasketItemResponse> addBasketItem(@PathVariable Long basketId, @RequestParam Long productId, @Valid @RequestBody BasketItemRequest basketItemRequest) {
        BasketItemResponse basketItemResponse = basketItemService.addBasketItem(basketItemRequest, productId, basketId);
        return new ResponseEntity<>(basketItemResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/baskets/{basketId}/basketItems/{basketItemsId}")
    public ResponseEntity<String> deleteBasketItem(@PathVariable Long basketId, @PathVariable Long basketItemsId) {
        basketItemService.deleteBasketItem(basketId, basketItemsId);
        return new ResponseEntity<>("BasketItem was deleted successfully", HttpStatus.OK);
    }
}
