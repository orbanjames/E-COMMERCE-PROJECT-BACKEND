package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Role;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface RoleDAO extends ReactiveCrudRepository<Role, Integer> {

    String BASIC_QUERY = "SELECT u.* FROM user_role u";

    @Query(BASIC_QUERY + " WHERE u.name = :name")
    Mono<Role> findByName(@Param("name") String name);
}

