package com.ecommerce_app.mappers;

import com.ecommerce_app.domain.entities.Product;
import com.ecommerce_app.dtos.ProductDetailsResponse;
import com.ecommerce_app.dtos.ProductRequest;
import com.ecommerce_app.dtos.ProductResponse;
import com.ecommerce_app.dtos.ProductShortResponse;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .productImage(product.getProductImage())
                .details(product.getDetails())
                .attributes(product.getAttributes())
                .productEvaluation(product.getProductEvaluation())
                .unitStock(product.getUnitStock())
                .build();
    }

    public static Product mapRequestToEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .productImage(productRequest.getProductImage())
                .details(productRequest.getDetails())
                .attributes(productRequest.getAttributes())
                .productEvaluation(productRequest.getProductEvaluation())
                .unitStock(productRequest.getUnitStock())
                .build();
    }

    public static ProductShortResponse mapToProductShortResponse(Product product) {
        return ProductShortResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .productImage(product.getProductImage())
                .build();
    }

    public static ProductDetailsResponse mapToProductDetailsResponse(Product product) {
        return ProductDetailsResponse.builder()
                .id(product.getId())
                .details(product.getDetails())
                .attributes(product.getAttributes())
                .productEvaluation(product.getProductEvaluation())
                .build();
    }
}
