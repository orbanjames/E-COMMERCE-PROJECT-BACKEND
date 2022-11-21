package com.jamesorban.ecommerceapplicationbackend.controllers;

import com.jamesorban.ecommerceapplicationbackend.config.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@SpringBootTest()
@AutoConfigureWebTestClient
public class BaseTest {
    private final static String SERVER_URL = "http://localhost:8080";
    private final static String TEST_USER = "test-user";
    private final static String TEST_PASSWORD = "password";
    private final static String ROLE_ADMIN = "ROLE_SYNOD_ADMIN";
    private final static String ROLE_USER = "ROLE_USER";

    @Autowired
    protected WebTestClient webTestClient;
    @Autowired
    protected JwtTokenProvider jwtTokenProvider;
    private String tokenString;
    protected String roleUserToken;
    @BeforeEach
    void setUp() {
        tokenString = jwtTokenProvider.createToken(new UsernamePasswordAuthenticationToken(TEST_USER, TEST_PASSWORD,
                AuthorityUtils.createAuthorityList(ROLE_ADMIN)));

        roleUserToken = jwtTokenProvider.createToken(new UsernamePasswordAuthenticationToken(TEST_USER, TEST_PASSWORD,
                AuthorityUtils.createAuthorityList(ROLE_USER)));
    }
    protected WebTestClient.RequestHeadersSpec<?> getWebTestClientRequest(WebTestClient webTestClient, String uri) {
        return webTestClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .headers(http -> http.setBearerAuth(tokenString));
    }
    protected WebTestClient.RequestBodySpec postWebTestClientRequest(WebTestClient webTestClient, String uri) {
        return webTestClient.post()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .headers(http -> http.setBearerAuth(tokenString));
    }

    protected WebTestClient getWebTestClientBindServer() {
        return WebTestClient.bindToServer().baseUrl(SERVER_URL).build();
    }

    protected String getJson(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }
}
