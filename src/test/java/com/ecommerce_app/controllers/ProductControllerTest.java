package com.ecommerce_app.controllers;

import com.ecommerce_app.dtos.ProductRequest;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static com.ecommerce_app.services.impl.fixtures.ProductFixture.productRequestFixture;
import static com.ecommerce_app.services.impl.fixtures.ProductFixture.productRequestFixtureWith;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.is;

public class ProductControllerTest extends AbstractControllerIntegrationTest {

    private static final String PRODUCTS_URL = BASE_API_URL + "/products";
    private static final String PRODUCTS_ID_URL = PRODUCTS_URL + API_ID;

    @Test
    public void shouldReturnProductList_whenCallGetProductsApi_withStatus200() {
        when()
                .get(PRODUCTS_URL)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnProductById_whenCallGetProductsApi_withStatus200() {
        when()
                .get(PRODUCTS_ID_URL, "3")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnCreatedProduct_whenCallPostProductsApi_withStatus200() {
        ProductRequest productRequest = productRequestFixture();
        given()
                .body(productRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(PRODUCTS_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", is(productRequest.getName()))
                .body("price", is(Float.parseFloat(productRequest.getPrice().toString())))
                .body("productImage", is(productRequest.getProductImage()))
                .body("details", is(productRequest.getDetails()))
                .body("attributes", is(productRequest.getAttributes()))
                .body("productEvaluation", is(Integer.parseInt(productRequest.getProductEvaluation().toString())))
                .body("unitStock", is(productRequest.getUnitStock()));
    }

    @Test
    public void shouldReturnBadRequest_whenCallPostProductsApi_withRequestWithoutName() {
        ProductRequest productRequest = productRequestFixtureWith(null, new BigDecimal("2500.0"), "img", "Smart", List.of("55 cal", "4K"), new BigInteger("5"), "11");
        given().body(productRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(PRODUCTS_URL)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldUpdatedProduct_whenCallPutProductsApi_withStatus200() {
        ProductRequest productRequest = productRequestFixture();
        given()
                .body(productRequest)
                .contentType(ContentType.JSON)
                .when()
                .put(PRODUCTS_ID_URL, "3")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void shouldDeletedProduct_whenCallDeleteProductApi_withStatus200() {
        when()
                .delete(PRODUCTS_ID_URL, "3")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn404_whenCallFindProductByIdToDelete_withNonExistsId() {
        when()
                .delete(PRODUCTS_ID_URL, "450")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}