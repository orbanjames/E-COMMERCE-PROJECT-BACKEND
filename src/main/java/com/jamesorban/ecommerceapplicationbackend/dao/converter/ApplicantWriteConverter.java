package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.Applicant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class ApplicantWriteConverter implements Converter<Applicant, OutboundRow> {

    @Override
    public OutboundRow convert(Applicant applyModel) {
        OutboundRow row = new OutboundRow();

        row.put("synodMember", Parameter.from(applyModel.getSynodMember().getId()));
        row.put("catalogue", Parameter.from(applyModel.getCatalogue().getId()));
        row.put("status", Parameter.from(applyModel.getStatus().toString()));
        return row;
    }
}