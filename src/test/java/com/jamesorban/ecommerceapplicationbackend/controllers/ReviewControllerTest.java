package com.jamesorban.ecommerceapplicationbackend.controllers;

import com.jamesorban.ecommerceapplicationbackend.dto.request.CreateReviewRequestDTO;
import com.jamesorban.ecommerceapplicationbackend.models.Applicant;
import com.jamesorban.ecommerceapplicationbackend.models.Review;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ApplicantStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ReviewStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ReviewControllerTest extends BaseTest {

    private static final String REVIEW_BASE = "/api/reviews";
    private static final String REVIEWS_CONF_USER = REVIEW_BASE + "/synods/2/users/2";
    private static final String REVIEWS_DECLINED = REVIEW_BASE + "/1/status?type=DECLINED";

    @MockBean
    private ReviewService reviewService;

    private Review reviewModel;
    private User committeeUserModel;
    private Applicant applicationModel;

    @BeforeEach
    void setup() {
        User userModel = User.builder().id(1).username("userUsername").build();
        committeeUserModel = User.builder().id(2).username("regUsername").build();
        applicationModel = Applicant.builder().id(1).synodMember(userModel).status(ApplicantStatusEnum.IN_REVIEW).build();
        reviewModel = Review.builder()
                .id(1)
                .applicant(applicationModel)
                .registrar(committeeUserModel)
                .status(ReviewStatusEnum.APPROVED)
                .build();
    }

    @Test
    void testGerReviews() {
        Flux<Review> reviewsFlux = Flux.just(reviewModel);
        when(reviewService.getReviews(2, 2)).thenReturn(reviewsFlux);

        getWebTestClientRequest(webTestClient, REVIEWS_CONF_USER)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Review.class)
                .value(response -> assertThat(response.get(0).getRegistrar().getUsername()).isEqualTo("regUsername"))
                .contains(reviewModel).hasSize(1);

    }

    @Test
    void testGerReviewsByApplication() {
        Flux<Review> reviewsFlux = Flux.just(reviewModel);
        when(reviewService.getReviews(1)).thenReturn(reviewsFlux);

        getWebTestClientRequest(webTestClient, REVIEW_BASE + "/applications/1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Review.class)
                .value(response -> assertThat(response.get(0).getRegistrar().getUsername()).isEqualTo("regUsername"))
                .contains(reviewModel).hasSize(1);

    }

    @Test
    void testChangeReviewStatus() {
        when(reviewService.changeStatus(1, ReviewStatusEnum.DECLINED)).thenReturn(Mono.empty().then());

        postWebTestClientRequest(webTestClient, REVIEWS_DECLINED)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    void testChangeReviewStatusForbidden() {
        webTestClient.post()
                .uri(REVIEWS_DECLINED)
                .accept(MediaType.APPLICATION_JSON)
                .headers(http -> http.setBearerAuth(roleUserToken))
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    void testCreateReview() {
        CreateReviewRequestDTO review = new CreateReviewRequestDTO();
        review.setApplication(applicationModel);
        review.setRegistrar(committeeUserModel);

        Review createdReview = Review.builder()
                .id(23)
                .applicant(applicationModel)
                .registrar(committeeUserModel)
                .status(ReviewStatusEnum.IN_PROGRESS)
                .build();

        when(reviewService.createReview(review.getApplication(), review.getRegistrar())).thenReturn(
                Mono.just(createdReview));
        postWebTestClientRequest(webTestClient, REVIEW_BASE)
                .body(Mono.just(review), CreateReviewRequestDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.body.registrar.id").isEqualTo(2)
                .jsonPath("$.body.applicant.synodMember.username").isEqualTo("userUsername")
                .jsonPath("$.body.status").isEqualTo(ReviewStatusEnum.IN_PROGRESS.toString());
    }


    @Test
    void testCreateReviewError() {
        CreateReviewRequestDTO review = new CreateReviewRequestDTO();
        review.setApplication(applicationModel);
        review.setRegistrar(committeeUserModel);
        when(reviewService.createReview(review.getApplication(), review.getRegistrar())).thenReturn(
                Mono.error(new DataIntegrityViolationException("User is already reviewer")));

        postWebTestClientRequest(webTestClient, REVIEW_BASE)
                .body(Mono.just(review), CreateReviewRequestDTO.class)
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.errorMessage").isEqualTo("User is already reviewer");
    }

    @Test
    void testCreateReviewForbidden() {
        CreateReviewRequestDTO review = new CreateReviewRequestDTO();
        webTestClient.post()
                .uri(REVIEW_BASE)
                .accept(MediaType.APPLICATION_JSON)
                .headers(http -> http.setBearerAuth(roleUserToken))
                .body(Mono.just(review), CreateReviewRequestDTO.class)
                .exchange()
                .expectStatus().isForbidden();
    }
}