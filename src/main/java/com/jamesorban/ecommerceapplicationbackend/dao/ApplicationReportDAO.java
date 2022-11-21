package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.ApplicationReport;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApplicationReportDAO extends ReactiveCrudRepository<ApplicationReport, Integer> {

    String BASIC_QUERY = "SELECT rc.* "
            + "FROM `application_report` rc "
            + "JOIN applicant a on rc.applicant = a.id";

    @Query(BASIC_QUERY)
    Flux<ApplicationReport> findAll();

    @Query(BASIC_QUERY + " WHERE rc.applicant = :applicationId ORDER BY rc.dateTime ASC")
    Flux<ApplicationReport> findByApplication(@Param("applicationId") int applicationId);

    @Query(BASIC_QUERY + " WHERE rc.user = :id ORDER BY rc.dateTime ASC")
    Mono<ApplicationReport> findByUser(@Param("id") int id);
}
