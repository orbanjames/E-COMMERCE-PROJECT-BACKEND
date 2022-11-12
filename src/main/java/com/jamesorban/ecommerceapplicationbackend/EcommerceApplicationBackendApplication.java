package com.jamesorban.ecommerceapplicationbackend;

import com.jamesorban.ecommerceapplicationbackend.dao.ApplicationReportDAO;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EcommerceApplicationBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplicationBackendApplication.class, args);
    }
    @Bean
    ApplicationRunner run(ApplicationReportDAO dao) {
        return args -> dao.findAll().subscribe(System.out::println);
    }
}
