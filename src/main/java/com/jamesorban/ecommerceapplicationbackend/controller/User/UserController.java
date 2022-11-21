package com.jamesorban.ecommerceapplicationbackend.controller.User;


import com.jamesorban.ecommerceapplicationbackend.models.User;
import com.jamesorban.ecommerceapplicationbackend.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{userId}")
    public Mono<User> getUser(@PathVariable int userId) {
        return userService.getByID(userId);
    }

    @GetMapping("/{userId}/payment/{paymentId}/status")
    public Mono<String> getCustomerPaymentStatus(@PathVariable int userId, @PathVariable int paymentId) {
        return userService.getUserPaymentStatus(paymentId, userId);
    }

    @GetMapping("/{userId}/synods/{synodId}/status")
    public Mono<String> getUserSynodStatus(@PathVariable int userId, @PathVariable int synodId) {
        return userService.getUserSynodStatus(synodId, userId);
    }
}