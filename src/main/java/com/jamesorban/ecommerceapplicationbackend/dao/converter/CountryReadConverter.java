package com.jamesorban.ecommerceapplicationbackend.dao.converter;


import com.jamesorban.ecommerceapplicationbackend.models.Country;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class CountryReadConverter implements Converter<Row, Country> {

    @Override
    public Country convert(Row source) {
        return Country.builder()
                .id(source.get("id", Integer.class))
                .name((source.get("name", String.class)))
                .isoCode(source.get("isoCode", String.class))
                .build();
    }
}
