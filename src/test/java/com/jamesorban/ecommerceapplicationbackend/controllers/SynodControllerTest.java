package com.jamesorban.ecommerceapplicationbackend.controllers;

import com.jamesorban.ecommerceapplicationbackend.models.*;
import com.jamesorban.ecommerceapplicationbackend.models.enums.UserStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.services.UserService;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SectionService;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SynodCategoryService;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SynodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


class SynodControllerTest extends BaseTest {

    private static final String SYNODS = "/api/synods";
    private static final String SYNOD = "/api/synods/1";
    private static final String SYNOD_USERS = "/api/synods/1/users";
    private static final String SYNOD_USER_STATUS = "/api/synods/1/users/1/is-member";
    private static final String SYNOD_REGISTER = "/api/synods/1/users/1/register";
    private static final String SYNOD_SECTIONS = "/api/synods/categories/1";


    @MockBean
    private SynodService synodService;

    @MockBean
    private UserService userService;

    @MockBean
    private SynodCategoryService synodCategoryService;

    @MockBean
    private SectionService sectionService;

    private Synod synod;
    private Synod synod2;

    @BeforeEach
    void setup() {
        synod = Synod.builder().id(1).name("Test synod 1").town("LONDON").isClosed(false).build();
        synod2 = Synod.builder().id(2).name("Test synod 2").town("ABUJA").isClosed(false).build();
    }

    @Test
    void testGetSynod() {
        when(synodService.getByID(1)).thenReturn(Mono.just(synod));

        getWebTestClientRequest(webTestClient, SYNOD)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Synod.class).isEqualTo(synod);
    }

    @Test
    void testGetSynodNotFound() {
        when(synodService.getByID(1)).thenReturn(Mono.empty());

        getWebTestClientRequest(webTestClient, SYNOD)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetSynods() {
        when(synodService.getAll()).thenReturn(Flux.just(synod, synod2));

        getWebTestClientRequest(webTestClient, SYNODS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Synod.class).contains(synod, synod2).hasSize(2);
    }

    @Test
    void testGetSynodMembers() {
        Role role = Role.builder().id(1).name("ROLE_USER").build();
        User userModel = User.builder().name("User 1").surname("Surname 1").role(role).build();
        User user2 = User.builder().name("User 2").surname("Surname 2").role(role).build();

        when(synodService.getSynodMembers(1)).thenReturn(Flux.just(userModel, user2));

        getWebTestClientRequest(webTestClient, SYNOD_USERS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class).contains(userModel, user2).hasSize(2);
    }

    @Test
    void testGetUserSynodStatus() {
        when(synodService.isUserRegisterToSynod(1, 1)).thenReturn(Mono.just(true));
        when(userService.getUserSynodStatus(1, 1)).thenReturn(Mono.just(UserStatusEnum.SYNOD_APPROVED.toString()));

        getWebTestClientRequest(webTestClient, SYNOD_USER_STATUS)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"status\":\"SYNOD_APPROVED\",\"userRegisterToSynod\":true}");
    }

    @Test
    void testGetUserSynodStatusNotRegister() {
        when(synodService.isUserRegisterToSynod(1, 1)).thenReturn(Mono.just(false));

        getWebTestClientRequest(webTestClient, SYNOD_USER_STATUS)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"userRegisterToSynod\":false}");
    }

    @Test
    void testRegisterToSynod() {
        when(synodService.registerToSynod(1, 1)).thenReturn(Mono.just(true));

        webTestClient.post()
                .uri(SYNOD_REGISTER)
                .accept(MediaType.APPLICATION_JSON)
                .headers(http -> http.setBearerAuth(roleUserToken))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class).isEqualTo(true);
    }

    @Test
    void testRegisterToSynodForbidden() {
        postWebTestClientRequest(webTestClient, SYNOD_REGISTER)
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    void testRegisterToSynodWrongMethod() {
        getWebTestClientRequest(webTestClient, SYNOD_REGISTER)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void testGetSections() {
        SynodCategory synodCategory = SynodCategory.builder().id(1).name("INTERNATIONAL YOUTH SYNOD").description("Description").build();
        SynodSection synodSection =
                SynodSection.builder().id(1).name("Section 1").category(synodCategory).build();
        SynodSection synodSection2 =
                SynodSection.builder().id(2).name("Section 2").category(synodCategory).build();

        when(sectionService.getSectionsForSynod(1)).thenReturn(Flux.just(synodSection, synodSection2));

        getWebTestClientRequest(webTestClient, SYNOD_SECTIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SynodSection.class).contains(synodSection, synodSection2).hasSize(2);
    }

    @Test
    void testGetSynodCategory() {
        SynodCategory synodCategory = SynodCategory.builder().id(1).name("INTERNATIONAL YOUTH SYNOD").description("Description").build();
        when(synodCategoryService.getByID(1)).thenReturn(Mono.just(synodCategory));

        getWebTestClientRequest(webTestClient, SYNOD_SECTIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SynodCategory.class).isEqualTo(synodCategory);
    }
}