package com.jamesorban.ecommerceapplicationbackend.services;

import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import com.jamesorban.ecommerceapplicationbackend.models.CatalogueCategory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatalogueService extends GenericService<Catalogue> {

    Flux<CatalogueCategory> getCategories();

    Mono<CatalogueCategory> getCategory(int categoryId);

    Mono<Catalogue> createCatalogue(Catalogue catalogue);
}
