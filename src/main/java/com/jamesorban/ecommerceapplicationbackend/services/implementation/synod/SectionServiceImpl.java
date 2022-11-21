package com.jamesorban.ecommerceapplicationbackend.services.implementation.synod;

import com.jamesorban.ecommerceapplicationbackend.dao.SectionDAO;
import com.jamesorban.ecommerceapplicationbackend.models.SynodSection;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SectionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class SectionServiceImpl implements SectionService {

    @Resource
    private SectionDAO sectionDAO;

    @Override
    public Flux<SynodSection> getAll() {
        return sectionDAO.findAll();
    }

    @Override
    public Mono<SynodSection> getByID(int id) {
        return sectionDAO.findById(id);
    }

    @Override
    public Flux<SynodSection> getSectionsForSynod(int synodId) {
        return sectionDAO.findSectionsForSynod(synodId);
    }

    @Override
    public Flux<SynodSection> getSectionsForCategory(int categoryId) {
        return sectionDAO.findSectionsForCategory(categoryId);
    }

    @Override
    public Flux<SynodSection> getSectionsForRegistrar(int registrarId) {
        return sectionDAO.findSectionsForRegistrar(registrarId);
    }
}
