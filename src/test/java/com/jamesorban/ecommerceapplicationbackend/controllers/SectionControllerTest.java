package com.jamesorban.ecommerceapplicationbackend.controllers;

import com.jamesorban.ecommerceapplicationbackend.models.SynodCategory;
import com.jamesorban.ecommerceapplicationbackend.models.SynodSection;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class SectionControllerTest extends BaseTest {

    private static final String SECTION_BASE = "/api/sections";
    private static final String SECTION_1 = SECTION_BASE + "/1";
    private static final String CATEGORY_PASTORAL_WORK = "PASTORAL-WORK";

    @MockBean
    private SectionService sectionService;

    private SynodSection synodSection;
    private SynodSection synodSection2;

    @BeforeEach
    void setup() {
        SynodCategory synodCategory = SynodCategory.builder().id(1).name(CATEGORY_PASTORAL_WORK).description("Description").build();
        synodSection = SynodSection.builder().id(1).name("Pastoral Administration section").category(synodCategory).build();
        synodSection2 = SynodSection.builder().id(2).name("Biblical work").category(synodCategory).build();
    }

    @Test
    void testGetSection() {
        Mono<SynodSection> synodSectionModelMono = Mono.just(synodSection);

        when(sectionService.getByID(1)).thenReturn(synodSectionModelMono);

        getWebTestClientRequest(webTestClient, SECTION_1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SynodSection.class)
                .value(response -> assertThat(response.getName()).isEqualTo("Pastoral Administration section"));
    }

    @Test
    void testGetSectionRealDB() throws IOException {
        getWebTestClientRequest(getWebTestClientBindServer(), SECTION_1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(getJson("json/section/section1.json"));
    }

    @Test
    void testGetSectionsOfRegistrar() {
        Flux<SynodSection> synodSectionFlux = Flux.just(synodSection, synodSection2);

        when(sectionService.getSectionsForRegistrar(1)).thenReturn(synodSectionFlux);

        getWebTestClientRequest(webTestClient, SECTION_BASE + "/registrar/1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SynodSection.class)
                .value(list -> assertThat(list.get(1).getName()).isEqualTo(synodSection2.getName()));
    }

    @Test
    void testGetSectionsOfCategory() {
        Flux<SynodSection> synodSectionFlux = Flux.just(synodSection, synodSection2);

        when(sectionService.getSectionsForCategory(1)).thenReturn(synodSectionFlux);

        getWebTestClientRequest(webTestClient, SECTION_BASE + "/categories/1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SynodSection.class)
                .value(list -> assertThat(list.get(1).getName()).isEqualTo(synodSection2.getName()))
                .value(list -> assertThat(list.get(0).getCategory().getName()).isEqualTo(CATEGORY_PASTORAL_WORK))
                .value(list -> assertThat(list.get(1).getCategory().getName()).isEqualTo(CATEGORY_PASTORAL_WORK))
                .hasSize(2);
    }
}