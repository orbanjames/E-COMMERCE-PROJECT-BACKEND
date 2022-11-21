package com.jamesorban.ecommerceapplicationbackend.services.implementation.synod;

import com.jamesorban.ecommerceapplicationbackend.dao.SynodDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.UserDAO;
import com.jamesorban.ecommerceapplicationbackend.models.Synod;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import com.jamesorban.ecommerceapplicationbackend.models.enums.UserStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SynodService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class SynodServiceImpl implements SynodService {

    @Resource
    private SynodDAO synodDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public Flux<Synod> getAll() {
        return synodDAO.findAll();
    }

    @Override
    public Mono<Synod> getByID(int id) {
        return synodDAO.findById(id);
    }

    @Override
    public Flux<User> getSynodMembers(int synodId) {
        return userDAO.findSynodUserBySynod(synodId);
    }

    @Override
    public Flux<User> getRegistrars(int synodId) {
        return userDAO.findRegistrarUserBySynod(synodId);
    }


    @Override
    public Mono<Boolean> isUserRegisterToSynod(int synodId, int userId) {
        return synodDAO.isUserRegisterToSynod(synodId, userId)
                .map(count -> count.equals(1));
    }

    @Override
    public Mono<Boolean> registerToSynod(int synodId, int userId) {
        return synodDAO.registerToSynod(synodId, userId, UserStatusEnum.PENDING_APPLICANT_REGISTRATION.toString())
                .map(count -> count.equals(1));
    }

    @Override
    public Flux<Synod> findSynodsForUser(User user) {
        return null;
    }
}
