package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Payment;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PaymentDAO extends ReactiveCrudRepository<Payment, Integer> {

    String BASIC_QUERY = "SELECT p.* FROM payment p";

    @Query(BASIC_QUERY + " WHERE p.status = :status")
    Mono<Payment> findByStatus(@Param("status") String status);
}

