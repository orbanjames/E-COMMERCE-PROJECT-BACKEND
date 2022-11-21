package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Synod;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SynodDAO extends ReactiveCrudRepository<Synod, Integer> {

    String BASIC_QUERY =
            "SELECT synod.*, c.name as countryName, c.isoCode as countryIsoCode, cc.name as categoryName, cc.description as categoryDesc "
                    + "FROM `synod` synod JOIN country c ON synod.country = c.id join synod_category cc on synod.synodCategory = cc.id";

    @Query(BASIC_QUERY)
    Flux<Synod> findAll();

    @Query(BASIC_QUERY + " WHERE synod.id = :id")
    Mono<Synod> findById(@Param("id") int id);

    @Query(BASIC_QUERY + " WHERE c.id = :countryId")
    Mono<Synod> findByCountry(@Param("countryId") int countryId);

    @Query("SELECT count(1) FROM `register_to_synod` WHERE user = :userId AND synod = :synodId")
    Mono<Integer> isUserRegisterToSynod(@Param("synodId") int synodId, @Param("userId") int userId);

    @Query("INSERT INTO `register_to_synod`(user, synod, status) VALUES (:userId,:synodId,:status)")
    Mono<Integer> registerToSynod(@Param("synodId") int synod, @Param("userId") int user, @Param("status") String status);

    @Query("SELECT synod.*, c.name as countryName, c.isoCode as countryIsoCode, sc.name as categoryName, sc.description as categoryDesc "
            + "FROM `register_to_synod` rc "
            + "JOIN synod synod on synod.id = rc.synod "
            + "JOIN Country c ON synod.country = c.id "
            + "JOIN synod_category sc on synod.synodCategory = sc.id "
            + "WHERE user = :userId")
    Flux<Synod> findSynodForUser(@Param("userId") int userId);
}
