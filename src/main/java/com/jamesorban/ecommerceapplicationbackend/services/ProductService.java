package com.jamesorban.ecommerceapplicationbackend.services;

import com.jamesorban.ecommerceapplicationbackend.models.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService extends GenericService<Product> {

    Flux<ProductCategory> getCategories();

    Mono<ProductCategory> getCategory(int categoryId);

    Flux<Company> getCompanies();

    Mono<Company> getCompany(int companyId);

    Flux<Color> getColors();

    Mono<Color> getColor(int colorId);

    Mono<Product> deleteProduct(int id);


    Mono<Product> createProduct(Product product);
}
