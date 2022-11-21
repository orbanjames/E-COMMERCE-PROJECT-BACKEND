package com.jamesorban.ecommerceapplicationbackend.config.security;


import com.jamesorban.ecommerceapplicationbackend.dao.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig implements WebFluxConfigurer {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtTokenProvider tokenProvider) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(it -> it
                        .pathMatchers(HttpMethod.GET, "/api/cart").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/products").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/payment").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/synods").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/users/titles").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/countries").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/login").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/**").authenticated()
                        .anyExchange().permitAll()
                )
                .addFilterAt(new JwtAuthorizationFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)
                .cors().and()
                .build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE").allowedOrigins("http://localhost:3000").allowCredentials(true);
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserDAO users) {

        return username -> users.findByUsername(username)
                .map(c -> User
                        .withUsername(c.getUsername())
                        .password(c.getPassword())
                        .build()
                );
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
                                                                       PasswordEncoder passwordEncoder) {
        var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024);
    }
}
