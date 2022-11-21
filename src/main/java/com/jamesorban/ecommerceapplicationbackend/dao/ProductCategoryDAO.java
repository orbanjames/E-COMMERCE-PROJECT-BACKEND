package com.jamesorban.ecommerceapplicationbackend.dao;



import com.jamesorban.ecommerceapplicationbackend.models.ProductCategory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductCategoryDAO extends ReactiveCrudRepository<ProductCategory, Integer> {

}
