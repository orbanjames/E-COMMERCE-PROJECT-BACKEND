package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.ApplicationReport;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class ApplicationReportWriteConverter implements Converter<ApplicationReport, OutboundRow> {

    @Override
    public OutboundRow convert(ApplicationReport applicant) {
        OutboundRow row = new OutboundRow();

        row.put("apply", Parameter.from(applicant.getApplicant().getId()));
        row.put("user", Parameter.from(applicant.getUser().getId()));
        row.put("text", Parameter.from(applicant.getText()));
        row.put("dateTime", Parameter.from(applicant.getDateTime()));
        return row;
    }
}
