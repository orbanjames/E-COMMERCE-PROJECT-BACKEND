package com.jamesorban.ecommerceapplicationbackend.dao.converter;


import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class CatalogueWriteConverter implements Converter<Catalogue, OutboundRow> {

    @Override
    public OutboundRow convert(Catalogue catalogue) {
        OutboundRow row = new OutboundRow();

        row.put("id", Parameter.from(catalogue.getId()));
        row.put("name", Parameter.from(catalogue.getName()));
        row.put("description", Parameter.from(catalogue.getDescription()));
        row.put("createdAt", Parameter.from(catalogue.getCreatedAt()));
        row.put("file", Parameter.from(catalogue.getFile()));
        row.put("fileName", Parameter.from(catalogue.getFileName()));
        row.put("user", Parameter.from(catalogue.getUser().getId()));
        row.put("synod", Parameter.from(catalogue.getSynod().getId()));
        row.put("category", Parameter.from(catalogue.getCategory().getId()));
        row.put("section", Parameter.from(catalogue.getSection().getId()));
        return row;
    }
}