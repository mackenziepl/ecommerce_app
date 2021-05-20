package com.ecommerce_app.controllers;

import com.ecommerce_app.dtos.ProductDetailsResponse;
import com.ecommerce_app.dtos.ProductRequest;
import com.ecommerce_app.dtos.ProductResponse;
import com.ecommerce_app.dtos.ProductShortResponse;
import com.ecommerce_app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductShortResponse>> getProducts() {
        List<ProductShortResponse> response = productService.getProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDetailsResponse> getProductDetails(@PathVariable Long id) {
        ProductDetailsResponse response = productService.getProductDetails(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.addProduct(productRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.updateProduct(id, productRequest);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product was removed successfully with id: " + id, HttpStatus.OK);
    }
}
