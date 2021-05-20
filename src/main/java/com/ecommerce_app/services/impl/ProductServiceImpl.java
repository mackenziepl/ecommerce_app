package com.ecommerce_app.services.impl;

import com.ecommerce_app.controllers.exceptions.ResourceNotFoundException;
import com.ecommerce_app.controllers.exceptions.ResourceNotUniqueException;
import com.ecommerce_app.domain.entities.Product;
import com.ecommerce_app.domain.repositories.ProductRepository;
import com.ecommerce_app.dtos.ProductDetailsResponse;
import com.ecommerce_app.dtos.ProductRequest;
import com.ecommerce_app.dtos.ProductResponse;
import com.ecommerce_app.dtos.ProductShortResponse;
import com.ecommerce_app.mappers.ProductMapper;
import com.ecommerce_app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductShortResponse> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::mapToProductShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailsResponse getProductDetails(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::mapToProductDetailsResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
    }

    @Transactional
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        validateProductNameExists(productRequest);
        Product product = ProductMapper.mapRequestToEntity(productRequest);
        productRepository.save(product);
        return ProductMapper.mapToProductResponse(product);
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .map(productFromDb -> {
                    productFromDb.setName(productRequest.getName());
                    productFromDb.setPrice(productRequest.getPrice());
                    productFromDb.setProductImage(productRequest.getProductImage());
                    productFromDb.setDetails(productRequest.getDetails());
                    productFromDb.setAttributes(productRequest.getAttributes());
                    productFromDb.setProductEvaluation(productRequest.getProductEvaluation());
                    productFromDb.setUnitStock(productRequest.getUnitStock());
                    return productRepository.save(productFromDb);
                        })
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));

        return ProductMapper.mapToProductResponse(product);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
        productRepository.delete(product);
    }

    private void validateProductNameExists(ProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.getName())) {
            throw new ResourceNotUniqueException("Product is already exist");
        }
    }
}
