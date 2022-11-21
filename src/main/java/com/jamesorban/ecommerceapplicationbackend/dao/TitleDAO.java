package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Title;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TitleDAO extends ReactiveCrudRepository<Title, Integer> {

}
