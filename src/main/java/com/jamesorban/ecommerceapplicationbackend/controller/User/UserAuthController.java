package com.jamesorban.ecommerceapplicationbackend.controller.User;

import com.jamesorban.ecommerceapplicationbackend.config.security.JwtAuthorizationFilter;
import com.jamesorban.ecommerceapplicationbackend.config.security.JwtTokenProvider;
import com.jamesorban.ecommerceapplicationbackend.dto.LoginDTO;
import com.jamesorban.ecommerceapplicationbackend.dto.ResponseDTO;
import com.jamesorban.ecommerceapplicationbackend.dto.request.AuthenticationRequestDTO;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import com.jamesorban.ecommerceapplicationbackend.services.UserService;
import com.jamesorban.ecommerceapplicationbackend.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Objects;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final JwtTokenProvider tokenProvider;

    private final ReactiveAuthenticationManager authenticationManager;

    @Resource
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/token")
    public Mono<ResponseEntity<LoginDTO>> login(@RequestBody Mono<AuthenticationRequestDTO> authRequest) {
        return authRequest
                .flatMap(loginRequest ->
                        authenticationManager.authenticate(getAuthenticationToken(loginRequest))
                                .flatMap(authentication -> {
                                    String token = tokenProvider.createToken(authentication);
                                    return userService.getByUsername(authentication.getName())
                                            .map(user -> new ResponseEntity<>(new LoginDTO(token, user),
                                                    getHeaders(token),
                                                    HttpStatus.OK));
                                }))
                .onErrorResume(error -> Mono.just(new ResponseEntity<>(new LoginDTO(true, error.getMessage()), HttpStatus.BAD_REQUEST)));

    }

    @PostMapping("/register")
    public Mono<ResponseEntity<ResponseDTO>> register(@RequestBody User user) {
        Errors errors = new BeanPropertyBindingResult(user, "User");
        userValidator.validate(user, errors);
        if (errors.hasErrors() && Objects.nonNull(errors.getFieldError())) {
            return Mono.just(ResponseEntity.badRequest().body(new ResponseDTO(null, errors.getFieldError().getDefaultMessage())));
        }

        return userService.register(user).map(registeredUser -> ResponseEntity.ok(new ResponseDTO(registeredUser, "")))
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest()
                        .body(new ResponseDTO(null, error.getCause().getMessage()))));
    }

    private HttpHeaders getHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, JwtAuthorizationFilter.HEADER_PREFIX + token);
        return httpHeaders;
    }

    private AbstractAuthenticationToken getAuthenticationToken(AuthenticationRequestDTO authenticationRequest) {
        return new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                new String(Base64.getDecoder().decode(authenticationRequest.getPassword())));
    }
}
