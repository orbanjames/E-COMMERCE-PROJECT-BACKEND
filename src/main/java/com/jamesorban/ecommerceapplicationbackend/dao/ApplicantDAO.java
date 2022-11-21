package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.Applicant;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApplicantDAO extends ReactiveCrudRepository<Applicant, Integer> {

    String BASIC_QUERY = "SELECT a.* FROM `applicant` a";

    String JOIN_CATALOGUE_QUERY =
            "SELECT a.*, c.name as catalogueName, c.description as catalogueDescription, c.createdAt as catalogueCreatedAt, "
                    + "c.file as catalogueFile, c.fileName as catalogueFileName, c.user, c.synod, c.catalogueCategory, c.section "
                    + "FROM `applicant` a "
                    + "JOIN catalogue c on a.catalogue = c.id";

    @Query(BASIC_QUERY)
    Flux<Applicant> findAll();

    @Query(JOIN_CATALOGUE_QUERY + " WHERE a.id = :id")
    Mono<Applicant> findById(@Param("id") int id);

    @Query(JOIN_CATALOGUE_QUERY + " WHERE a.id = :id AND c.synod = :synodId")
    Mono<Applicant> findByIdAndSynod(@Param("id") int id, @Param("synodId") int synodId);

    @Query(JOIN_CATALOGUE_QUERY + " WHERE c.synod = :synodId and c.user = :userId")
    Flux<Applicant> findByUserAndSynod(@Param("synodId") int synodId, @Param("userId") int userId);

    @Query(JOIN_CATALOGUE_QUERY + " WHERE c.synod = :synodId and c.section = :sectionId")
    Flux<Applicant> findBySynodSection(@Param("synodId") int synodId, @Param("sectionId") int sectionId);

    @Query("UPDATE applicant SET status = :status WHERE id = :appId")
    Mono<Void> updateStatus(@Param("status") String status, @Param("appId") int appId);
}