package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Review;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewDAO extends ReactiveCrudRepository<Review, Integer> {

    String BASIC_QUERY = "SELECT r.* FROM `review` r";

    String APPLICANT_JOIN_QUERY = "SELECT r.*, a.status as 'applicantStatus', a.catalogue, a.synodMember, "
            + "d.name as catalogueName, d.description as catalogueDescription, d.createdAt as catalogueCreatedAt, d.file as catalogueFile, "
            + "d.fileName as catalogueFileName, d.user, d.createdAt, d.catalogueCategory, d.section, "
            + "u.name as 'cm_userName', u.surname 'cm_userSurname', u.email as 'cm_userEmail', u.username as 'cm_userUsername', "
            + "u.password as 'cm_userPassword', u.title as 'cm_userTitle', u.country as 'cm_userCountry', u.role as 'cm_userRole', u.id as cm_user "
            + "FROM `review` r "
            + "JOIN applicant a on r.applicant = a.id "
            + "JOIN catalogue d on a.catalogue= d.id "
            + "JOIN user u on r.registrar = u.id";

    @Query(BASIC_QUERY)
    Flux<Review> findAll();

    @Query(APPLICANT_JOIN_QUERY + " WHERE r.id = :id")
    Mono<Review> findById(@Param("id") int id);

    @Query(APPLICANT_JOIN_QUERY + " WHERE r.registrar = :registrarId AND a.status = :applicantStatus AND d.synod = :synodId")
    Flux<Review> findByRegistrarIdAndApplicantStatus(@Param("registrar") int registrarId,
                                                            @Param("synodId") int synodId,
                                                            @Param("applicantStatus") String applicantStatus);

    @Query(APPLICANT_JOIN_QUERY + " WHERE r.registrar = :registrarId AND r.applicant = :applicantId")
    Mono<Review> findByRegistrarIdAndApplicant(@Param("registrar") int registrarId,
                                                      @Param("applicantId") int applicantId);

    @Query(APPLICANT_JOIN_QUERY + " WHERE r.applicant = :id")
    Flux<Review> findByApplicant(@Param("id") int id);

    @Query(BASIC_QUERY + " WHERE r.status = :status")
    Mono<Review> findByStatus(@Param("status") String status);

    @Query(APPLICANT_JOIN_QUERY + " where a.synodMember = :userId and d.synod = :synodId")
    Flux<Review> findBySynodRegistrar(@Param("userId") int userId, @Param("synodId") int synodId);

    @Query("UPDATE review SET status = :status WHERE id = :reviewId")
    Mono<Void> updateStatus(@Param("status") String status, @Param("reviewId") int reviewId);
}
