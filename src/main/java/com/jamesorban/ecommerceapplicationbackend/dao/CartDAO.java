package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Cart;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartDAO extends ReactiveCrudRepository<Cart, Integer> {

    String BASIC_QUERY =
            "SELECT DISTINCT ct.* "
                    + "FROM `cart` ct ";


    @Query(BASIC_QUERY)
    Flux<Cart> findAll();

    @Query(BASIC_QUERY + " WHERE ct.id = :id")
    Mono<Cart> findById(@Param("id") int id);

}