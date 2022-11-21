package com.jamesorban.ecommerceapplicationbackend.controller;


import com.jamesorban.ecommerceapplicationbackend.models.Product;
import com.jamesorban.ecommerceapplicationbackend.services.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import javax.annotation.Resource;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ProductController {


    @Resource
    private ProductService productService;


    @GetMapping("/products/{id}")
    public Mono<Product> getSingleProduct(@PathVariable int id) {
        return productService.getByID(id);
    }


    @GetMapping("/products")
    public Flux<Product> getAllProducts() {
        return productService.getAll();
    }



}
