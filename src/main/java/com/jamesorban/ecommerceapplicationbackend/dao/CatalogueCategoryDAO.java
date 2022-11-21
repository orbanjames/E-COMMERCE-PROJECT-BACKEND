package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.CatalogueCategory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CatalogueCategoryDAO extends ReactiveCrudRepository<CatalogueCategory, Integer> {

}