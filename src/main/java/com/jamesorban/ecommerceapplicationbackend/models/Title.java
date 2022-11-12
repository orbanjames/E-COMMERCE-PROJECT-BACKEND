package com.jamesorban.ecommerceapplicationbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("title")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Title {

    @Id
    private int id;

    private String name;
}
