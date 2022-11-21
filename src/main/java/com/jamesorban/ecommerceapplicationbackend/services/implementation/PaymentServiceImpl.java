package com.jamesorban.ecommerceapplicationbackend.services.implementation;


import com.jamesorban.ecommerceapplicationbackend.dao.PaymentDAO;
import com.jamesorban.ecommerceapplicationbackend.models.Payment;
import com.jamesorban.ecommerceapplicationbackend.services.PaymentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    public static final String USER = "USER";

    @Resource
    private PaymentDAO paymentDAO;

    @Override
    public Flux<Payment> getAll() {
        return paymentDAO.findAll();
    }

    @Override
    public Mono<Payment> getByID(int id) {
        return paymentDAO.findById(id);
    }

    @Override
    public Mono<Payment> getByStatus(String paymentStatus) {
        return paymentDAO.findByStatus(paymentStatus);
    }
}