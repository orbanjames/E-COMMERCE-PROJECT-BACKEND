package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.SynodSection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class SectionWriteConverter implements Converter<SynodSection, OutboundRow> {

    @Override
    public OutboundRow convert(SynodSection source) {
        OutboundRow row = new OutboundRow();

        row.put("id", Parameter.from(source.getId()));
        row.put("name", Parameter.from(source.getName()));
        row.put("category", Parameter.from(source.getCategory().getId()));
        return row;
    }
}
