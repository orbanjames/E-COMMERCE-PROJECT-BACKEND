package com.jamesorban.ecommerceapplicationbackend.controllers;
import com.jamesorban.ecommerceapplicationbackend.models.*;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ApplicantStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.services.ApplicantService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

class ApplicantControllerTest extends BaseTest {

    private static final String APPLICATIONS_BASE = "/api/applications";
    private static final String APPLICATIONS = "/api/applications/synods/1/users/1";
    private static final String APPLICATION = "/api/applications/1";
    private static final String APPLICATION2 = "/api/applications/1/synods/1";
    private static final String APPLICATIONS_FOR_REVIEW = "/api/applications/synods/1/registrar/3";
    private static final String APPLICATION_REPORTS = "/api/applications/1/reports";
    private static final String APPLICATION_REPORT_DELETE = "/api/applications/reports/1/users/1";
    private static final String APPLICATION_CLOSE = "/api/applications/1/close";
    private static final String APPLICATION_DELETE = "/api/applications/1/delete";
    private static final String APPLICATION_FINISH = "/api/applications/1/finish";

    @MockBean
    private ApplicantService applicantService;

    private Applicant application;
    private Applicant application2;
    private Synod synod;
    private ApplicationReport applicationReport;
    private Catalogue catalogue;


    @BeforeEach
    void setup() {
        Role role = Role.builder().id(1).name("ROLE_USER").build();
        User user = User.builder().id(1).name("User 1").surname("Surname 1").role(role).build();
        User user2 = User.builder().id(2).name("User 2").surname("Surname 2").role(role).build();

        synod = Synod.builder().id(1).name("PASTORAL SYNOD").town("LAGOS").isClosed(false).build();
        byte[] file = new byte[10];

        catalogue = Catalogue.builder()
                .id(10)
                .name("God in Human Representation")
                .description("for spiritual Growth")
                .fileName("Test file")
                .file(file)
                .user(user)
                .build();

        Catalogue catalogue2 = Catalogue.builder()
                .id(10)
                .name("Test document1")
                .description("Description")
                .fileName("Test file")
                .file(file)
                .user(user)
                .build();

        application =
                Applicant.builder().id(1).synodMember(user).catalogue(catalogue).status(ApplicantStatusEnum.IN_REVIEW).build();
        application2 =
                Applicant.builder().id(2).synodMember(user2).catalogue(catalogue2).status(ApplicantStatusEnum.IN_REVIEW).build();

        applicationReport = ApplicationReport.builder().id(1).text("Text 1").applicant(application).user(user).build();
    }

    @Test
    void testGetApplication() {
        when(applicantService.getByID(1)).thenReturn(Mono.just(application));

        getWebTestClientRequest(webTestClient, APPLICATION)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Applicant.class).isEqualTo(application);
    }

    @Test
    void testGetApplication2() {
        when(applicantService.getApplication(1, synod.getId())).thenReturn(Mono.just(application));

        getWebTestClientRequest(webTestClient, APPLICATION2)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Applicant.class).isEqualTo(application);
    }

    @Test
    void testGetApplications() {
        when(applicantService.getByUserAndSynod(synod.getId(), 1)).thenReturn(Flux.just(application, application2));

        getWebTestClientRequest(webTestClient, APPLICATIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Applicant.class).hasSize(2).contains(application, application2);
    }

    @Test
    void testGetApplicationsForReview() {
        when(applicantService.getApplicationsForRegistrar(synod.getId(), 3))
                .thenReturn(Flux.just(application, application2));

        getWebTestClientRequest(webTestClient, APPLICATIONS_FOR_REVIEW)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.body[0].synodMember.id").isEqualTo(1)
                .jsonPath("$.body[0].catalogue.name").isEqualTo("God in Human Representation")
                .jsonPath("$.body[1].catalogue.name").isEqualTo("Test document1")
                .jsonPath("$.body[1].status").isEqualTo(ApplicantStatusEnum.IN_REVIEW.toString())
                .jsonPath("$.errorMessage").isEqualTo(StringUtils.EMPTY);

    }

    @Test
    void testGetApplicationsForReviewError() {
        when(applicantService.getApplicationsForRegistrar(synod.getId(), 3))
                .thenReturn(Flux.error(new Exception("User is not part of the synod")));

        getWebTestClientRequest(webTestClient, APPLICATIONS_FOR_REVIEW)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errorMessage").isEqualTo("User is not part of the synod");

    }

    @Test
    void testGetCommentsForApplication() {
        when(applicantService.getReportForApplication(application.getId())).thenReturn(Flux.just(applicationReport));

        getWebTestClientRequest(webTestClient, APPLICATION_REPORTS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ApplicationReport.class).hasSize(1).contains(applicationReport);

    }

    @Test
    void testAddCommentsForApplication() {
        when(applicantService.createReport(applicationReport)).thenReturn(Mono.just(applicationReport));

        postWebTestClientRequest(webTestClient, APPLICATION_REPORTS)
                .body(Mono.just(applicationReport), ApplicationReport.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApplicationReport.class).isEqualTo(applicationReport);
    }

    @Test
    void testDeleteComment() {
        when(applicantService.deleteReport(1, 1)).thenReturn(Mono.empty().then());

        webTestClient.delete()
                .uri(APPLICATION_REPORT_DELETE)
                .accept(MediaType.APPLICATION_JSON)
                .headers(http -> http.setBearerAuth(roleUserToken))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCloseApplication() {
        when(applicantService.closeApplication(application.getId())).thenReturn(Mono.empty().then());

        postWebTestClientRequest(webTestClient, APPLICATION_CLOSE)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDeleteApplication() {
        when(applicantService.deleteApplication(application.getId())).thenReturn(Mono.empty().then());

        webTestClient.delete()
                .uri(APPLICATION_DELETE)
                .accept(MediaType.APPLICATION_JSON)
                .headers(http -> http.setBearerAuth(roleUserToken))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFinishApplication() {
        when(applicantService.finishApplication(application.getId())).thenReturn(Mono.empty().then());

        postWebTestClientRequest(webTestClient, APPLICATION_FINISH)
                .body(Mono.just(applicationReport), ApplicationReport.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFinishApplicationError() {
        when(applicantService.finishApplication(application.getId())).thenReturn(Mono.error(new Exception("You must have 2 approvals")));

        postWebTestClientRequest(webTestClient, APPLICATION_FINISH)
                .body(Mono.just(applicationReport), ApplicationReport.class)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errorMessage").isEqualTo("You must have 2 approvals");
    }

    @Test
    void testCreateApply() {
        String jsonDocument = "{\n"
                + "\t\"id\": 10,\n"
                + "\t\"name\": \"Test document1\",\n"
                + "\t\"description\": \"Description\",\n"
                + "\t\"createdAt\": null,\n"
                + "\t\"file\": \"AAAAAAAAAAAAAA==\",\n"
                + "\t\"fileName\": \"Test file\",\n"
                + "\t\"user\": {\n"
                + "\t\t\"id\": 0,\n"
                + "\t\t\"name\": \"User 2\",\n"
                + "\t\t\"surname\": \"Surname 2\",\n"
                + "\t\t\"email\": null,\n"
                + "\t\t\"username\": null,\n"
                + "\t\t\"title\": null,\n"
                + "\t\t\"country\": null,\n"
                + "\t\t\"role\": {\n"
                + "\t\t\t\"id\": 1,\n"
                + "\t\t\t\"name\": \"ROLE_USER\"\n"
                + "\t\t}\n"
                + "\t}\n"
                + "}";

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("file", new ClassPathResource("/images/ORBAN.png")).contentType(MediaType.MULTIPART_FORM_DATA);
        multipartBodyBuilder.part("God in Human Representation", jsonDocument).contentType(MediaType.MULTIPART_FORM_DATA);

        postWebTestClientRequest(webTestClient, APPLICATIONS_BASE)
                .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
                .exchange()
                .expectStatus().isOk();
    }
}