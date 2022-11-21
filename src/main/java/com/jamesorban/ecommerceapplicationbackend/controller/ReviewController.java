package com.jamesorban.ecommerceapplicationbackend.controller;

import com.jamesorban.ecommerceapplicationbackend.dto.ResponseDTO;
import com.jamesorban.ecommerceapplicationbackend.dto.request.CreateReviewRequestDTO;
import com.jamesorban.ecommerceapplicationbackend.models.Review;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ReviewStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ReviewController {

    @Resource
    private ReviewService reviewService;

    @GetMapping("/synods/{synodId}/users/{userId}")
    public ResponseEntity<Flux<Review>> getReviews(@PathVariable int userId, @PathVariable int synodId) {
        return ResponseEntity.ok(reviewService.getReviews(userId, synodId));
    }

    @GetMapping("/applications/{applicantId}")
    public ResponseEntity<Flux<Review>> getReviews(@PathVariable int applicantId) {
        return ResponseEntity.ok(reviewService.getReviews(applicantId));
    }

    @GetMapping("/synods/{synodId}/registrar/{registrarId}/applicant-status/{status}")
    public ResponseEntity<Flux<Review>> getReviews(@PathVariable int synodId,
                                                        @PathVariable int registrarId,
                                                        @PathVariable String status) {
        return ResponseEntity.ok(reviewService.getReviews(registrarId, synodId, status));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Mono<Review>> getReview(@PathVariable int reviewId) {
        return ResponseEntity.ok(reviewService.getByID(reviewId));
    }

    @GetMapping("/applications/{applicantId}/registrar/{registrarId}")
    public ResponseEntity<Mono<Review>> getReview(@PathVariable int applicantId, @PathVariable int registrarId) {
        return ResponseEntity.ok(reviewService.getReview(registrarId, applicantId));
    }

    @PostMapping("/{reviewId}/status")
    @PreAuthorize("hasRole('ROLE_REGISTRAR') or hasRole('SYNOD_ADMIN')")
    public Mono<ResponseEntity<Void>> changeReviewStatus(@PathVariable int reviewId, @RequestParam final ReviewStatusEnum type) {
        return reviewService.changeStatus(reviewId, type).map(ResponseEntity::ok);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_REGISTRAR') or hasRole('SYNOD_ADMIN')")
    public Mono<ResponseEntity<ResponseDTO>> createReview(@RequestBody CreateReviewRequestDTO review) {
        return reviewService.createReview(review.getApplication(), review.getRegistrar())
                .map(reviewModel -> ResponseEntity.ok(new ResponseDTO(reviewModel, "")))
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(new ResponseDTO(null, error.getMessage()))));
    }
}
