package com.jamesorban.ecommerceapplicationbackend.controllers;

import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import com.jamesorban.ecommerceapplicationbackend.models.CatalogueCategory;
import com.jamesorban.ecommerceapplicationbackend.services.CatalogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


class CatalogueControllerTest extends BaseTest {
    private static final String CATALOGUE = "/api/catalogues/1";
    private static final String CATALOGUES_FILE = "/api/catalogues/1/file";
    private static final String CATALOGUES_CATEGORIES = "/api/catalogues/categories";
    @MockBean
    private CatalogueService catalogueService;
    private Catalogue catalogue;
    private byte[] file;
    @BeforeEach
    void setup() {
        file = new byte[10];
        catalogue = Catalogue.builder()
                .id(10)
                .name("Test catalogue")
                .description("Description")
                .fileName("Test file")
                .file(file)
                .build();
    }
    @Test
    void testGeCatalogue() {
        when(catalogueService.getByID(1)).thenReturn(Mono.just(catalogue));

        getWebTestClientRequest(webTestClient, CATALOGUE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Catalogue.class).isEqualTo(catalogue);
    }

    @Test
    void testGetCatalogueFileForDownload() {
        when(catalogueService.getByID(1)).thenReturn(Mono.just(catalogue));

        getWebTestClientRequest(webTestClient, CATALOGUES_FILE)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentDisposition(ContentDisposition.attachment().filename("Test file").build())
                .expectHeader().contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .expectHeader().cacheControl(CacheControl.noCache())
                .expectBody(byte[].class).isEqualTo(file);
    }

    @Test
    void testGeCatalogueCategories() {
        CatalogueCategory catalogueCategory = CatalogueCategory.builder().id(1).name("PASTORAL WORK").build();
        CatalogueCategory catalogueCategory2 = CatalogueCategory.builder().id(2).name("THE ROSARY").build();

        when(catalogueService.getCategories()).thenReturn(Flux.just(catalogueCategory, catalogueCategory2));

        getWebTestClientRequest(webTestClient, CATALOGUES_CATEGORIES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CatalogueCategory.class).hasSize(2).contains(catalogueCategory, catalogueCategory2);
    }
}