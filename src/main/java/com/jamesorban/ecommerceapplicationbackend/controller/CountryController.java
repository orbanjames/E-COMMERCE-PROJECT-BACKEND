package com.jamesorban.ecommerceapplicationbackend.controller;



import com.jamesorban.ecommerceapplicationbackend.models.Country;
import com.jamesorban.ecommerceapplicationbackend.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Resource
    private CountryService countryService;

    @GetMapping
    public Flux<Country> getAll() {
        return countryService.getAll();
    }
}
