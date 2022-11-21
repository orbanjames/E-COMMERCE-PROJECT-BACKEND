package com.jamesorban.ecommerceapplicationbackend.services.implementation;

import com.jamesorban.ecommerceapplicationbackend.dao.CountryDAO;
import com.jamesorban.ecommerceapplicationbackend.models.Country;
import com.jamesorban.ecommerceapplicationbackend.services.CountryService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryDAO countryDAO;

    @Override
    public Flux<Country> getAll() {
        return countryDAO.findAll();
    }

    @Override
    public Mono<Country> getByID(int id) {
        return countryDAO.findById(id);
    }
}
