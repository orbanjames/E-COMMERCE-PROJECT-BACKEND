package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CompanyDAO extends ReactiveCrudRepository<Company, Integer> {

}
