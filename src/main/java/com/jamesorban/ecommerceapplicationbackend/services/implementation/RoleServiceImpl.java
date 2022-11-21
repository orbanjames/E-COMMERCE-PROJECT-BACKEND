package com.jamesorban.ecommerceapplicationbackend.services.implementation;

import com.jamesorban.ecommerceapplicationbackend.dao.RoleDAO;
import com.jamesorban.ecommerceapplicationbackend.models.Role;
import com.jamesorban.ecommerceapplicationbackend.services.RoleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_REGISTRAR = "REGISTRAR";

    @Resource
    private RoleDAO roleDAO;

    @Override
    public Flux<Role> getAll() {
        return roleDAO.findAll();
    }

    @Override
    public Mono<Role> getByID(int id) {
        return roleDAO.findById(id);
    }

    @Override
    public Mono<Role> getByName(String roleName) {
        return roleDAO.findByName(roleName);
    }
}
