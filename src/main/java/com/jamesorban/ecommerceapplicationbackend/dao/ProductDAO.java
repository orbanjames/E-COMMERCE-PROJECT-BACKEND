package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDAO extends ReactiveCrudRepository<Product, Integer> {

    String BASIC_QUERY =
            "SELECT p.*, c.name as 'categoryName', com.name as 'companyName', col.type as 'colorType' "
                    + "FROM `product` p "
                    + "JOIN color col on p.color = col.id "
                    + "JOIN company com on p.company = com.id "
                    + "JOIN category c on p.category = c.id";

    @Query(BASIC_QUERY)
    Flux<Product> findAll();

    @Query(BASIC_QUERY + " WHERE p.id = :id")
    Mono<Product> findById(@Param("id") int id);
}
