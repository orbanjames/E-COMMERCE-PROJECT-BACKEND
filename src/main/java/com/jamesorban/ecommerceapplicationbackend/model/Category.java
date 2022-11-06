package com.jamesorban.ecommerceapplicationbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;


@Table("category")
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private int id;

    @NotNull
    private String name;
}
