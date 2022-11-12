package com.jamesorban.ecommerceapplicationbackend.models;

import com.jamesorban.ecommerceapplicationbackend.models.enums.ReviewStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("review")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor public class Review {

    @Id
    private int id;

    private User registrar;

    private Applicant applicant;

    private ReviewStatusEnum status;
}
