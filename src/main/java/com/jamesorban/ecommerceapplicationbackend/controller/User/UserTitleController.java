package com.jamesorban.ecommerceapplicationbackend.controller.User;

import com.jamesorban.ecommerceapplicationbackend.models.Title;
import com.jamesorban.ecommerceapplicationbackend.services.TitleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users/titles")
public class UserTitleController {

    @Resource
    private TitleService titleService;

    @GetMapping
    public Flux<Title> getAll() {
        return titleService.getAll();
    }
}
