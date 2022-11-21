package com.jamesorban.ecommerceapplicationbackend.services.synod;

import com.jamesorban.ecommerceapplicationbackend.models.Synod;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import com.jamesorban.ecommerceapplicationbackend.services.GenericService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface SynodService extends GenericService<Synod> {

    Flux<User> getSynodMembers(int synodId);

    Flux<User> getRegistrars(int synodId);

    Mono<Boolean> isUserRegisterToSynod(int synodId, int userId);

    Mono<Boolean> registerToSynod(int synodId, int userId);

    Flux<Synod> findSynodsForUser(User user);
}
