package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.dao.converter.helper.ConverterHelper;
import com.jamesorban.ecommerceapplicationbackend.models.*;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ApplicantStatusEnum;
import com.jamesorban.ecommerceapplicationbackend.models.enums.ReviewStatusEnum;
import io.r2dbc.spi.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@ReadingConverter
public class ReviewReadConverter implements Converter<Row, Review> {

    private final static Logger LOG = LoggerFactory.getLogger(ReviewReadConverter.class);

    @Override
    public Review convert(Row source) {
        return Review.builder()
                .id(source.get("id", Integer.class))
                .applicant(getApplicant(source))
                .registrar(getUser(source))
                .status(ReviewStatusEnum.valueOf(source.get("status", String.class)))
                .build();
    }

    private Applicant getApplicant(Row source) {
        try {
            return Applicant.builder()
                    .id(source.get("apply", Integer.class))
                    .status(ApplicantStatusEnum.valueOf(source.get("applicantStatus", String.class)))
                    .catalogue(getCatalogue(source))
                    .synodMember(User.builder().id(source.get("synodRegistrant", Integer.class)).build())
                    .build();
        } catch (NoSuchElementException ex) {
            LOG.error(ex.getMessage());
            return Applicant.builder()
                    .id(source.get("applicant", Integer.class))
                    .build();
        }
    }

    private User getUser(Row source) {
        try {
            return ConverterHelper.getUser(source, "rg");
        } catch (NoSuchElementException ex) {
            LOG.error(ex.getMessage());
            return User.builder().id(source.get("registrar", Integer.class)).build();
        }
    }

    public static Catalogue getCatalogue(Row source) {
        try {
            return Catalogue.builder()
                    .id(source.get("catalogue", Integer.class))
                    .name(source.get("catalogueName", String.class))
                    .description(source.get("catalogueDescription", String.class))
                    .createdAt(source.get("catalogueCreatedAt", LocalDate.class))
                    .file(source.get("catalogueFile", byte[].class))
                    .fileName(source.get("catalogueFileName", String.class))
                    .user(User.builder().id(source.get("user", Integer.class)).build())
                    .category(CatalogueCategory.builder().id(source.get("category", Integer.class)).build())
                    .section(SynodSection.builder().id(source.get("section", Integer.class)).build())
                    .synod(Synod.builder().id(source.get("synod", Integer.class)).build())
                    .build();
        } catch (NoSuchElementException ex) {
            LOG.error(ex.getMessage());
            return Catalogue.builder()
                    .id(source.get("catalogue", Integer.class))
                    .build();
        }
    }
}
