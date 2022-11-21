
package com.jamesorban.ecommerceapplicationbackend.services.implementation;

import com.jamesorban.ecommerceapplicationbackend.dao.UserDAO;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import com.jamesorban.ecommerceapplicationbackend.services.RoleService;
import com.jamesorban.ecommerceapplicationbackend.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Base64;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Resource
    private RoleService roleService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Flux<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public Mono<User> getByID(int id) {
        return userDAO.findById(id);
    }

    @Override
    public Mono<User> getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public Mono<String> getUserPaymentStatus(int paymentId, int userId) {
        return userDAO.findUserPaymentStatus(paymentId, userId);
    }

    @Override
    public Mono<String> getUserSynodStatus(int synodId, int userId) {
        return userDAO.findUserSynodStatus(synodId, userId);
    }

    @Override
    public Mono<User> register(User user) {
        return roleService.getByName(RoleServiceImpl.ROLE_USER).flatMap(role -> {
            user.setRole(role);
            user.setPassword(passwordEncoder.encode(new String(Base64.getDecoder().decode(user.getPassword()))));
            return userDAO.save(user);
        });
    }
}
