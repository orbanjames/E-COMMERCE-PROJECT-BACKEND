package com.jamesorban.ecommerceapplicationbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;


@Table("color")
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    @Id
    private int id;

    @NotNull
    private String type;
}
