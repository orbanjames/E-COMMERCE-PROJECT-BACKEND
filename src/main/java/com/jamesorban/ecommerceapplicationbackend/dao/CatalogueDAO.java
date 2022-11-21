package com.jamesorban.ecommerceapplicationbackend.dao;


import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface CatalogueDAO extends ReactiveCrudRepository<Catalogue, Integer> {

    String BASIC_QUERY =
            "SELECT c.*, cac.name as 'categoryName', cac.description as 'categoryDescription', "
                    + "u.name as 'userName', u.surname 'userSurname', u.email as 'userEmail', u.username as 'userUsername', "
                    + "u.password as 'userPassword', u.title as 'userTitle', u.country as 'userCountry', u.role as 'userRole',\n"
                    + "sy.id as 'synodId', sy.name as 'synodName', sy.town as 'synodTown', sy.closed as 'synodIsClosed', "
                    + "sy.date as 'synodDate', sy.country as 'synodCountry', sy.synodCategory as 'synodCategory' \n"
                    + "FROM `catalogue` c "
                    + "JOIN User u on c.user = u.id "
                    + "JOIN synod sy on c.synod = sy.id "
                    + "JOIN catalogue_category cac ON c.synod = cac.id";

    @Query(BASIC_QUERY)
    Flux<Catalogue> findAll();

    @Query(BASIC_QUERY + " WHERE c.id = :id")
    Mono<Catalogue> findById(@Param("id") int id);
}
