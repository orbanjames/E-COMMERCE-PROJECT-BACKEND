package com.jamesorban.ecommerceapplicationbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;


@Table("cart")
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private int id;

    @NotNull
    private Product product;

    private int quantity;

    private int price;

    private String subtotal;
}
