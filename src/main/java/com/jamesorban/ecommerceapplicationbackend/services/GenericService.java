package com.jamesorban.ecommerceapplicationbackend.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService<T> {
    Flux<T> getAll();
    Mono<T> getByID(int id);
}
