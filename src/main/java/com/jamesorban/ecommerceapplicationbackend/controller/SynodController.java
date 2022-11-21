package com.jamesorban.ecommerceapplicationbackend.controller;


import com.jamesorban.ecommerceapplicationbackend.dto.UserStatusDTO;
import com.jamesorban.ecommerceapplicationbackend.models.Synod;
import com.jamesorban.ecommerceapplicationbackend.models.SynodCategory;
import com.jamesorban.ecommerceapplicationbackend.models.SynodSection;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import com.jamesorban.ecommerceapplicationbackend.services.UserService;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SectionService;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SynodCategoryService;
import com.jamesorban.ecommerceapplicationbackend.services.synod.SynodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/synods")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class SynodController {

    @Resource
    private SynodService synodService;

    @Resource
    private UserService userService;

    @Resource
    private SynodCategoryService synodCategoryService;

    @Resource
    private SectionService sectionService;

    @GetMapping
    public Flux<Synod> getAllSynods() {
        return synodService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Synod>> getSynod(@PathVariable int id) {
        return synodService.getByID(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }


//     Returning participants of the synod
//      @param synodId id of synod for which participants are returned
//      @return Flux<User>

    @GetMapping("/{synodId}/users")
    public Flux<User> getSynodMembers(@PathVariable int synodId) {
        return synodService.getSynodMembers(synodId);
    }


//      Returning users that are registrants of the synod
//      @param synodId id of synod
//      @return Flux<User>

    @GetMapping("/{synodId}/registrars")
    public Flux<User> getRegistrars(@PathVariable int synodId) {
        return synodService.getRegistrars(synodId);
    }

    @GetMapping("/{synodId}/users/{userId}/is-member")
    public Mono<UserStatusDTO> getUserSynodStatus(@PathVariable int synodId, @PathVariable int userId) {
        return synodService.isUserRegisterToSynod(synodId, userId).flatMap(isRegistered -> {
            if (isRegistered) {
                return userService.getUserSynodStatus(synodId, userId).map(status -> new UserStatusDTO(true, status));
            }
            return userService.getUserSynodStatus(synodId, userId).map(status -> new UserStatusDTO(false, status));
        });
    }

    @PostMapping(("/{synodId}/users/{userId}/register"))
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<Boolean> registerToSynod(@PathVariable int synodId, @PathVariable int userId) {
        return synodService.registerToSynod(synodId, userId);
    }

    @GetMapping("/{synodId}/sections")
    public Flux<SynodSection> getSections(@PathVariable int synodId) {
        return sectionService.getSectionsForSynod(synodId);
    }

    @GetMapping("/categories")
    public Flux<SynodCategory> getCategories() {
        return synodCategoryService.getAll();
    }

    @GetMapping("/categories/{categoryId}")
    public Mono<SynodCategory> getCategory(@PathVariable int categoryId) {
        return synodCategoryService.getByID(categoryId);
    }
}
