package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.Color;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class ColorReadConverter implements Converter<Row, Color> {

    @Override
    public Color convert(Row source) {
        return Color.builder()
                .id(source.get("id", Integer.class))
                .type((source.get("type", String.class)))
                .build();
    }
}
