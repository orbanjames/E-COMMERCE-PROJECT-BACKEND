package com.jamesorban.ecommerceapplicationbackend.controllers;

import com.jamesorban.ecommerceapplicationbackend.models.Product;
import com.jamesorban.ecommerceapplicationbackend.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


class ProductControllerTest extends BaseTest {
    private static final String PRODUCT = "/api/products/1";
    private static final String PRODUCT_CATEGORIES = "/api/products/categories";
    @MockBean
    private ProductService productService;
    private Product product;

    @BeforeEach
    void setup() {
        product = Product.builder()
                .id(10)
                .name("Dinnig Table")
                .description("for your comfort")
                .price(35)
                .build();
    }

    @Test
    void testGeProduct() {
        when(productService.getByID(1)).thenReturn(Mono.just(product));

        getWebTestClientRequest(webTestClient, PRODUCT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class).isEqualTo(product);
    }

}