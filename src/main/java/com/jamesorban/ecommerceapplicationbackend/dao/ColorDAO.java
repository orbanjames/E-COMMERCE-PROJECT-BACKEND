package com.jamesorban.ecommerceapplicationbackend.dao;


import com.jamesorban.ecommerceapplicationbackend.models.Color;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ColorDAO extends ReactiveCrudRepository<Color, Integer> {

}
