package com.jamesorban.ecommerceapplicationbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO implements Serializable {

    private static final long serialVersionUID = -7586746375915710875L;

    private String username;
    private String password;

}