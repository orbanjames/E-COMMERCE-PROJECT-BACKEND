package com.jamesorban.ecommerceapplicationbackend.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jwt")
@Component
@Data
public class JwtProperties {
    private String secretKey = "ecommerce_key_prjdajeidkwwioeiskak";
    private long validityInMs = 24000000; // 6 H
}