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
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ecommerce_app.services.impl.fixtures.ProductFixture.productFixture;
import static com.ecommerce_app.services.impl.fixtures.ProductFixture.productListFixture;
import static com.ecommerce_app.services.impl.fixtures.ProductFixture.productRequestFixture;
import static com.ecommerce_app.services.impl.fixtures.ProductFixture.productRequestFixtureWithExistingNameInDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProductServiceImplTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductService productService = new ProductServiceImpl(productRepository);

    @Test
    public void shouldReturnProductList_whenCallService_findAll() {
        //  given
        List<Product> products = productListFixture();
        List<ProductResponse> productResponses = products.stream()
                .map(ProductMapper::mapToProductResponse)
                .collect(Collectors.toList());
        //  when
        Mockito.when(productRepository.findAll()).thenReturn(products);
        List<ProductShortResponse> allProductsList = productService.getProducts();
        //  then
        assertEquals(allProductsList.size(), productResponses.size());
        assertEquals(allProductsList.get(0).getId(), productResponses.get(0).getId());
    }

    @Test
    public void shouldReturnProductDetails_whenCallService_findById_withExistsId() {
        //  given
        Product product = productFixture();
        //  when
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        ProductDetailsResponse productById = productService.getProductDetails(product.getId());
        //  then
        assertEquals(product.getId(), productById.getId());
        assertEquals(product.getDetails(), productById.getDetails());
        assertEquals(product.getAttributes(), productById.getAttributes());
        assertEquals(product.getProductEvaluation(), productById.getProductEvaluation());
    }

    @Test
    public void shouldAddedProduct_whenIncomingRequest() {
        //  given
        ProductRequest productRequest = productRequestFixture();
        //  when
        productService.addProduct(productRequest);
        //  then
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(1)).save(productCaptor.capture());
    }

    @Test(expected = ResourceNotUniqueException.class) //  then
    public void shouldThrownResourceNotUniqueToSqlException_whenRepositoryReturnExistingName() {
        //  given
        Mockito.when(productRepository.existsByName(any())).thenReturn(true);
        //  when
        productService.addProduct(productRequestFixtureWithExistingNameInDatabase());
    }

    @Test(expected = ResourceNotFoundException.class)  //  then
    public void shouldThrownResourceNotFoundException_whenRepositoryReturnEmptyOptional() {
        //  given
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());
        //  when
        productService.updateProduct(any(), productRequestFixture());
    }

    @Ignore
    public void shouldDeleteProduct_whenIncomingEntity() {
        //  given
        Product product = productFixture();
        //  when
        productService.deleteProduct(product.getId());
        //  then
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(1)).delete(productCaptor.capture());
    }
}