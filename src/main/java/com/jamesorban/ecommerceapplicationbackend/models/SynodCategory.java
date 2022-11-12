package com.jamesorban.ecommerceapplicationbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Builder;


import java.io.Serializable;

@Table("synod_category")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SynodCategory implements Serializable {

    @Id
    private int id;

    private String name;

    private String description;
}
