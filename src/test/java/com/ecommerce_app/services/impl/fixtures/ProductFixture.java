package com.ecommerce_app.services.impl.fixtures;

import com.ecommerce_app.domain.entities.Product;
import com.ecommerce_app.dtos.ProductRequest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextLong;

public class ProductFixture {

    public static List<Product> productListFixture() {
        return List.of(productFixture());
    }


    public static Product productFixture() {
        return Product.builder()
                .id(nextLong(0, 10))
                .name("TV Samsung")
                .price(new BigDecimal("2500.0"))
                .productImage("img")
                .details("Smart")
                .attributes(List.of("55 cal", "4K"))
                .productEvaluation(new BigInteger("5"))
                .unitStock("11")
                .build();
    }

    public static ProductRequest productRequestFixture() {
        return ProductRequest.builder()
                .name("TV Samsung")
                .price(new BigDecimal("2500.0"))
                .productImage("img")
                .details("Smart")
                .attributes(List.of("55 cal", "4K"))
                .productEvaluation(new BigInteger("5"))
                .unitStock("11")
                .build();
    }

    public static ProductRequest productRequestFixtureWithExistingNameInDatabase() {
        return ProductRequest.builder()
                .name("TV Samsung")
                .price(new BigDecimal("2500.0"))
                .productImage("img")
                .details("Smart")
                .attributes(List.of("55 cal", "4K"))
                .productEvaluation(new BigInteger("5"))
                .unitStock("11")
                .build();
    }

    public static ProductRequest productRequestFixtureWith(String name, BigDecimal price, String productImage, String details,
                                                           List<String> attributes, BigInteger productEvaluation, String unitStock) {
        return ProductRequest.builder()
                .name(name)
                .price(price)
                .productImage(productImage)
                .details(details)
                .attributes(attributes)
                .productEvaluation(productEvaluation)
                .unitStock(unitStock)
                .build();
    }
}
