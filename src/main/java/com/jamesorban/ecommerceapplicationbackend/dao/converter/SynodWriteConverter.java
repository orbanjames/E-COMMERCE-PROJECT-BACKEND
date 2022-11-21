package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.Synod;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class SynodWriteConverter implements Converter<Synod, OutboundRow> {

    @Override
    public OutboundRow convert(Synod synod) {
        OutboundRow row = new OutboundRow();

        row.put("id", Parameter.from(synod.getId()));
        row.put("name", Parameter.from(synod.getName()));
        row.put("date", Parameter.from(synod.getDate()));
        row.put("town", Parameter.from(synod.getTown()));
        row.put("isClosed", Parameter.from(synod.isClosed()));
        row.put("synodCategory", Parameter.from(synod.getSynodCategory().getId()));
        row.put("country", Parameter.from(synod.getCountry().getId()));
        return row;
    }
}