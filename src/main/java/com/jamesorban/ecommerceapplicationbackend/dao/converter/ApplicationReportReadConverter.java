package com.jamesorban.ecommerceapplicationbackend.dao.converter;


import com.jamesorban.ecommerceapplicationbackend.models.Applicant;
import com.jamesorban.ecommerceapplicationbackend.models.ApplicationReport;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDateTime;

@ReadingConverter
public class ApplicationReportReadConverter implements Converter<Row, ApplicationReport> {

    @Override
    public ApplicationReport convert(Row source) {
        return ApplicationReport.builder()
                .id(source.get("id", Integer.class))
                .text(source.get("text", String.class))
                .applicant(getApplicant(source))
                .user(User.builder().id(source.get("user", Integer.class)).build())
                .dateTime(source.get("dateTime", LocalDateTime.class))
                .build();
    }

    private Applicant getApplicant(Row source) {
        return Applicant.builder()
                .id(source.get("id", Integer.class))
                .build();
    }
}