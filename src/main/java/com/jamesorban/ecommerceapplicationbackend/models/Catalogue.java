package com.jamesorban.ecommerceapplicationbackend.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("catalogue")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Catalogue {

    @Id
    private int id;

    private String name;

    private String description;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createdAt;

    private byte[] file;

    private String fileName;

    private User user;

    private Synod synod;

    private CatalogueCategory category;

    private SynodSection section;
}
