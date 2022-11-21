package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.Color;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class ColorWriteConverter implements Converter<Color, OutboundRow> {

    @Override
    public OutboundRow convert(Color source) {
        OutboundRow row = new OutboundRow();

        row.put("id", Parameter.from(source.getId()));
        row.put("type", Parameter.from(source.getType()));
        return row;
    }

}

