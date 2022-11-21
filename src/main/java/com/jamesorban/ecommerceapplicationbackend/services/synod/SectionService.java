package com.jamesorban.ecommerceapplicationbackend.services.synod;


import com.jamesorban.ecommerceapplicationbackend.models.SynodSection;
import com.jamesorban.ecommerceapplicationbackend.services.GenericService;
import reactor.core.publisher.Flux;

public interface SectionService extends GenericService<SynodSection> {

    Flux<SynodSection> getSectionsForSynod(int synodId);

    Flux<SynodSection> getSectionsForCategory(int categoryId);

    Flux<SynodSection> getSectionsForRegistrar(int registrarId);
}