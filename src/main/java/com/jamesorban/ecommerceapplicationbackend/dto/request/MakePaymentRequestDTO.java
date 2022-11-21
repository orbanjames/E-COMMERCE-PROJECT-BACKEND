package com.jamesorban.ecommerceapplicationbackend.dto.request;

import com.jamesorban.ecommerceapplicationbackend.models.Cart;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakePaymentRequestDTO {

    private User user;
    private Cart cart;
}

