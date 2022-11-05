package com.jamesorban.ecommerceapplicationbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table("User")
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private int id;

    @NotNull
    private String name;

    private String surname;

    @Pattern(regexp = "([a-z])+@([a-z])+\\.com", message = "Not valid email")
    private String email;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
