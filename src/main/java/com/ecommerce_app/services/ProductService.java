package com.ecommerce_app.services;

import com.ecommerce_app.dtos.ProductDetailsResponse;
import com.ecommerce_app.dtos.ProductRequest;
import com.ecommerce_app.dtos.ProductResponse;
import com.ecommerce_app.dtos.ProductShortResponse;

import java.util.List;

public interface ProductService {

    List<ProductShortResponse> getProducts();

    ProductDetailsResponse getProductDetails(Long id);

    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);
}
