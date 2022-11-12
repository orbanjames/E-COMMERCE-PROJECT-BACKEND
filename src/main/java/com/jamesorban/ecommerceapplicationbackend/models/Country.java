package com.jamesorban.ecommerceapplicationbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("country")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country implements Serializable {

    @Id
    private int id;

    private String name;

    private String isoCode;
}