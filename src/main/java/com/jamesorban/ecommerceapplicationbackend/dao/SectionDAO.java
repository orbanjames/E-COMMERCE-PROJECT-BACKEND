package com.jamesorban.ecommerceapplicationbackend.dao;

import com.jamesorban.ecommerceapplicationbackend.models.SynodSection;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionDAO extends ReactiveCrudRepository<SynodSection, Integer> {

    String BASIC_QUERY =
            "SELECT sec.*, cc.name as categoryName, cc.description as categoryDesc "
                    + "FROM `synod_section` sec JOIN synod_category cc ON sec.category = cc.id";

    String QUERY_SYNOD_SECTION_TO_SYNOD =
            "SELECT sec.id as 'id', sec.name as 'name', cc.id as category, cc.name as categoryName, cc.description as categoryDesc\n"
                    + "FROM `synod_section2_synod` con2sec "
                    + "JOIN synod_section sec on con2sec.section = sec.id "
                    + "JOIN synod_category cc on sec.category = cc.id";

    String QUERY_SYNOD_SECTION_TO_REGISTRAR =
            "SELECT sec.id as 'id', sec.name as 'name', cc.id as category, cc.name as categoryName, cc.description as categoryDesc "
                    + "FROM user_register2_synod_section cm2s "
                    + "JOIN synod_section sec on cm2s.section = sec.id "
                    + "JOIN synod_category cc on sec.category = cc.id";
    @Query(BASIC_QUERY)
    Flux<SynodSection> findAll();

    @Query(BASIC_QUERY + " WHERE sec.id = :id")
    Mono<SynodSection> findById(@Param("id") int id);

    @Query(QUERY_SYNOD_SECTION_TO_SYNOD + " WHERE synod = :synodID")
    Flux<SynodSection> findSectionsForSynod(@Param("synodID") int synodId);

    @Query(BASIC_QUERY + " WHERE sec.category = :categoryId")
    Flux<SynodSection> findSectionsForCategory(@Param("categoryId") int categoryId);

    @Query("SELECT count(1) FROM `synod_section2_synod` WHERE section = :sectionId AND synod = :synodId")
    Mono<Integer> isSectionBelongsToSynod(@Param("synodId") int synodId, @Param("sectionId") int sectionId);

    @Query(QUERY_SYNOD_SECTION_TO_REGISTRAR + " WHERE cm2s.registrar = :registrar")
    Flux<SynodSection> findSectionsForRegistrar(@Param("registrar") int registrar);

}
