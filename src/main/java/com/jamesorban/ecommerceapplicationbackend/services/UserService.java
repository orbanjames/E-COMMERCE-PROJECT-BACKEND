package com.jamesorban.ecommerceapplicationbackend.services;


import com.jamesorban.ecommerceapplicationbackend.models.User;
import reactor.core.publisher.Mono;

public interface UserService extends GenericService<User> {
    Mono<User> getByUsername(String username);

    Mono<String> getUserPaymentStatus(int paymentId, int userId);

    Mono<String> getUserSynodStatus(int synodId, int userId);

    Mono<User> register(User user);
}

