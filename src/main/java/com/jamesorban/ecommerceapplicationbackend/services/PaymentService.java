package com.jamesorban.ecommerceapplicationbackend.services;

import com.jamesorban.ecommerceapplicationbackend.models.Payment;
import reactor.core.publisher.Mono;

public interface PaymentService extends GenericService<Payment> {


    Mono<Payment> getByStatus(String paymentStatus);

}
