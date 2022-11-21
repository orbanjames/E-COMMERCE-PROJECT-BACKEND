package com.jamesorban.ecommerceapplicationbackend.services.implementation;


import com.jamesorban.ecommerceapplicationbackend.dao.ProductCategoryDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.ColorDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.CompanyDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.ProductDAO;
import com.jamesorban.ecommerceapplicationbackend.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest()
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductDAO productDAO;
    @Mock
    private ProductCategoryDAO productCategoryDAO;
    @Mock
    private CompanyDAO companyDAO;
    @Mock
    private ColorDAO colorDAO;
    private Product product;
    private Product product2;
    private ProductCategory productCategory;
    private ProductCategory productCategory2;
    private Company company;
    private Company company2;
    private Color color;
    private Color color2;

    @BeforeEach
    void setUp() {

        product = Product.builder()
                .id(1)
                .name("Product Test")
                .description("Description")
                .price(39)
                .build();

        product2 = Product.builder()
                .id(5)
                .name("Test product2")
                .description("Description2")
                .price(59)
                .build();

        productCategory = ProductCategory.builder().id(1).name("dinning").build();
        productCategory2 = ProductCategory.builder().id(2).name("wooden bar").build();

        company = Company.builder().id(1).name("caressa").build();
        company2 = Company.builder().id(2).name("liddy").build();

        color = Color.builder().id(1).type("blue").build();
        color2 = Color.builder().id(2).type("red").build();
    }

    @Test
    void testGetAll() {
        when(productDAO.findAll()).thenReturn(Flux.just(product, product2));
        StepVerifier.create(productService.getAll())
                .expectSubscription()
                .expectNext(product)
                .assertNext(product -> assertThat(product.getName()).isEqualTo("Test product2"))
                .expectComplete()
                .verify();

    }

    @Test
    void testGetByID() {
        when(productDAO.findById(1)).thenReturn(Mono.just(product));
        StepVerifier.create(productService.getByID(1))
                .expectSubscription()
                .assertNext(product -> assertThat(product.getName()).isEqualTo("Product Test"))
                .expectComplete()
                .verify();

    }

    @Test
    void testGetAllCategories() {
        when(productCategoryDAO.findAll()).thenReturn(Flux.just(productCategory, productCategory2));
        StepVerifier.create(productService.getCategories())
                .expectSubscription()
                .expectNext(productCategory)
                .assertNext(category -> assertThat(category.getName()).isEqualTo("wooden bar"))
                .expectComplete()
                .verify();

    }

    @Test
    void testGetCategory() {
        when(productCategoryDAO.findById(1)).thenReturn(Mono.just(productCategory));
        StepVerifier.create(productService.getCategory(1))
                .expectSubscription()
                .expectNext(productCategory)
                .expectComplete()
                .verify();

    }


    @Test
    void testGetAllCompanies() {
        when(companyDAO.findAll()).thenReturn(Flux.just(company, company2));
        StepVerifier.create(productService.getCompanies())
                .expectSubscription()
                .expectNext(company)
                .assertNext(company -> assertThat(company.getName()).isEqualTo("liddy"))
                .expectComplete()
                .verify();

    }

    @Test
    void testGetCompany() {
        when(companyDAO.findById(1)).thenReturn(Mono.just(company));
        StepVerifier.create(productService.getCompany(1))
                .expectSubscription()
                .expectNext(company)
                .expectComplete()
                .verify();

    }


    @Test
    void testGetAllColors() {
        when(colorDAO.findAll()).thenReturn(Flux.just(color, color2));
        StepVerifier.create(productService.getColors())
                .expectSubscription()
                .expectNext(color)
                .assertNext(color -> assertThat(color.getType()).isEqualTo("red"))
                .expectComplete()
                .verify();

    }

    @Test
    void testGetColor() {
        when(colorDAO.findById(1)).thenReturn(Mono.just(color));
        StepVerifier.create(productService.getColor(1))
                .expectSubscription()
                .expectNext(color)
                .expectComplete()
                .verify();

    }

//    @Test
//    void testCreateProduct() {
//        when(productDAO.save(any())).thenReturn(Mono.just(product));
//        StepVerifier.create(productService.createProduct(product))
//                .expectSubscription()
//                .expectNext(product)
//                .expectComplete()
//                .verify();
//    }


}