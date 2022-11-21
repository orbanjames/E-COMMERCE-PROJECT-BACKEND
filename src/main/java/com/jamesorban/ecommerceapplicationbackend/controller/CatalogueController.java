package com.jamesorban.ecommerceapplicationbackend.controller;

import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import com.jamesorban.ecommerceapplicationbackend.models.CatalogueCategory;
import com.jamesorban.ecommerceapplicationbackend.services.CatalogueService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/catalogues")
public class CatalogueController {

    @Resource
    private CatalogueService catalogueService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Catalogue>> getCatalogue(@PathVariable int id) {
        return catalogueService.getByID(id).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}/file")
    public Mono<ResponseEntity<byte[]>> getCatalogueFileForDownload(@PathVariable int id) {
        return catalogueService.getByID(id).map(catalogue -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + catalogue.getFileName());
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            return ResponseEntity.ok().headers(headers).body(catalogue.getFile());
        });
    }

    @GetMapping("/categories")
    public Flux<CatalogueCategory> getCatalogueCategories() {
        return catalogueService.getCategories();
    }

    @GetMapping("/categories/{categoryId}")
    public Mono<CatalogueCategory> getCatalogueCategory(@PathVariable int categoryId) {
        return catalogueService.getCategory(categoryId);
    }
}
