package com.jamesorban.ecommerceapplicationbackend.services.implementation;

import com.jamesorban.ecommerceapplicationbackend.dao.TitleDAO;
import com.jamesorban.ecommerceapplicationbackend.models.Title;
import com.jamesorban.ecommerceapplicationbackend.services.TitleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class TitleServiceImpl implements TitleService {

    @Resource
    private TitleDAO titleDAO;

    @Override
    public Flux<Title> getAll() {
        return titleDAO.findAll();
    }

    @Override
    public Mono<Title> getByID(int id) {
        return titleDAO.findById(id);
    }
}