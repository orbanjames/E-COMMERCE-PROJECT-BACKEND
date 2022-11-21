package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserDAO extends ReactiveCrudRepository<User, Integer> {

    String BASIC_QUERY =
            "SELECT DISTINCT u.*, c.id as countryId, c.name as countryName, c.isoCode as countryIsoCode, r.name as roleName, t.name as titleName "
                    + "FROM `User` u "
                    + "join Country c ON u.country = c.id "
                    + "join user_role r on u.role = r.id "
                    + "join Title t on u.title = t.id ";

    String SYNOD_QUERY = BASIC_QUERY
            + "join register_to_synod reg on reg.user = u.id "
            + "join Synod sy on sy.id = reg.synod";

    String PAYMENT_QUERY = BASIC_QUERY
            + "join registertomakepayment reg on reg.user = u.id "
            + "join payment Pay on Pay.id = reg.payment";


    @Query(BASIC_QUERY)
    Flux<User> findAll();

    @Query(BASIC_QUERY + " WHERE u.id = :id")
    Mono<User> findById(@Param("id") int id);

    @Query(BASIC_QUERY + " WHERE username = :username")
    Mono<User> findByUsername(@Param("username") String username);

    @Query(SYNOD_QUERY + " WHERE sy.id = :synodId AND r.name = 'USER' AND reg.status = 'SYNOD_APPROVED'")
    Flux<User> findSynodUserBySynod(@Param("synodId") int synodId);

    @Query(SYNOD_QUERY + " WHERE sy.id = :synodId AND r.name = 'REGISTRAR' AND reg.status = 'SYNOD_APPROVED'")
    Flux<User> findRegistrarUserBySynod(@Param("synodId") int synodId);

    @Query("SELECT status FROM `register_to_synod` WHERE user = :userId and synod = :synodId")
    Mono<String> findUserSynodStatus(@Param("synodId") int synodId, @Param("userId") int userId);


    @Query("SELECT status FROM `RegisterToMakePayment` WHERE user = :userId and payment = :paymentId")
    Mono<String> findUserPaymentStatus(@Param("paymentId") int paymentId, @Param("userId") int userId);

    @Query(PAYMENT_QUERY + " WHERE pay.id = :paymentId AND u.name = 'USER' AND reg.status = 'SUCCESSFUL'")
    Flux<User> findPaymentByUsername(@Param("paymentId") int paymentId);

}



