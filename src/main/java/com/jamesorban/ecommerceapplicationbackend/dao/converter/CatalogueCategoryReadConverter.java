package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.CatalogueCategory;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class CatalogueCategoryReadConverter implements Converter<Row, CatalogueCategory> {

    @Override
    public CatalogueCategory convert(Row source) {
        return CatalogueCategory.builder()
                .id(source.get("id", Integer.class))
                .name((source.get("name", String.class)))
                .description(source.get("description", String.class))
                .build();
    }
}