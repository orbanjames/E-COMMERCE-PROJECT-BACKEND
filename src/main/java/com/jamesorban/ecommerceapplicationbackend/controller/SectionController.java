package com.jamesorban.ecommerceapplicationbackend.controller;

import com.jamesorban.ecommerceapplicationbackend.models.SynodSection;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    @Resource
    private SectionService sectionService;

    @GetMapping("/{sectionId}")
    public Mono<SynodSection> getSection(@PathVariable int sectionId) {
        return sectionService.getByID(sectionId);
    }

    @GetMapping("/categories/{categoryId}")
    public Flux<SynodSection> getSectionsOfCategory(@PathVariable int categoryId) {
        return sectionService.getSectionsForCategory(categoryId);
    }

    @GetMapping("/registrar/{registrarId}")
    public Flux<SynodSection> getSectionsForRegistrar(@PathVariable int registrarId) {
        return sectionService.getSectionsForRegistrar(registrarId);
    }
}