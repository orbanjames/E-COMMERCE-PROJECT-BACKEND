package com.jamesorban.ecommerceapplicationbackend.services;

import com.jamesorban.ecommerceapplicationbackend.models.Role;
import reactor.core.publisher.Mono;

public interface RoleService extends GenericService<Role> {

    Mono<Role> getByName(String roleName);
}

