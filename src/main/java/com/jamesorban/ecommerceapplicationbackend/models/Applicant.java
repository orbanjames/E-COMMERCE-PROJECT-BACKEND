package com.jamesorban.ecommerceapplicationbackend.models;

import com.jamesorban.ecommerceapplicationbackend.models.enums.ApplicantStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("applicant")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

    @Id
    private int id;

    private User synodMember;

    private Catalogue catalogue;

    private ApplicantStatusEnum status;
}