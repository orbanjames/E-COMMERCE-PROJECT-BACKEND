package com.jamesorban.ecommerceapplicationbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;


@Table("product")
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private int id;

    @NotNull
    private String name;

    private String description;

    private int price;

    private SynodCategory synodCategory;
    private Company company;
    private Color color;
}
