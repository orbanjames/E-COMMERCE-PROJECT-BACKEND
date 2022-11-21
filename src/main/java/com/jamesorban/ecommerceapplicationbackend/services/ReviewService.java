package com.jamesorban.ecommerceapplicationbackend.services;

import com.jamesorban.ecommerceapplicationbackend.models.Applicant;
import com.jamesorban.ecommerceapplicationbackend.models.Review;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ReviewStatusEnum;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService extends GenericService<Review> {

    Mono<Review> getReview(int registrarId, int applicantID);

    Flux<Review> getReviews(int userId, int synodId);

    Flux<Review> getReviews(int Registrar, int synodId, String applicantStatus);

    Flux<Review> getReviews(int applicantID);

    Mono<Void> changeStatus(int reviewId, ReviewStatusEnum status);

    Mono<Review> createReview(Applicant application, User registrar);
}
