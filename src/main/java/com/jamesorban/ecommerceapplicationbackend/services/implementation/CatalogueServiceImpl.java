package com.jamesorban.ecommerceapplicationbackend.services.implementation;

import com.jamesorban.ecommerceapplicationbackend.dao.CatalogueCategoryDAO;
import com.jamesorban.ecommerceapplicationbackend.dao.CatalogueDAO;
import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import com.jamesorban.ecommerceapplicationbackend.models.CatalogueCategory;
import com.jamesorban.ecommerceapplicationbackend.services.CatalogueService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class CatalogueServiceImpl implements CatalogueService {

    @Resource
    private CatalogueDAO catalogueDAO;

    @Resource
    private CatalogueCategoryDAO catalogueCategoryDAO;

    @Override
    public Flux<Catalogue> getAll() {
        return catalogueDAO.findAll();
    }

    @Override
    public Mono<Catalogue> getByID(int id) {
        return catalogueDAO.findById(id);
    }

    @Override
    public Flux<CatalogueCategory> getCategories() {
        return catalogueCategoryDAO.findAll();
    }

    @Override
    public Mono<CatalogueCategory> getCategory(int categoryId) {
        return catalogueCategoryDAO.findById(categoryId);
    }

    @Override
    public Mono<Catalogue> createCatalogue(Catalogue catalogue) {
        return catalogueDAO.save(catalogue);
    }
}
