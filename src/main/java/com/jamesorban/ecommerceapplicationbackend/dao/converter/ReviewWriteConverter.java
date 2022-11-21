package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.Review;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

public class ReviewWriteConverter implements Converter<Review, OutboundRow> {

    @Override
    public OutboundRow convert(Review review) {
        OutboundRow row = new OutboundRow();

        row.put("id", Parameter.from(review.getId()));
        row.put("registrar", Parameter.from(review.getRegistrar().getId()));
        row.put("applicant", Parameter.from(review.getApplicant().getId()));
        row.put("status", Parameter.from(review.getStatus().toString()));
        return row;
    }

}