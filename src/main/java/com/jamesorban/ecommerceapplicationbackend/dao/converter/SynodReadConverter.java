package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.Country;
import com.jamesorban.ecommerceapplicationbackend.models.Synod;
import com.jamesorban.ecommerceapplicationbackend.models.SynodCategory;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDate;

@ReadingConverter
public class SynodReadConverter implements Converter<Row, Synod> {

    @Override
    public Synod convert(Row source) {
        return Synod.builder()
                .id(source.get("id", Integer.class))
                .name(source.get("name", String.class))
                .date(source.get("date", LocalDate.class))
                .town(source.get("town", String.class))
                .isClosed(source.get("closed", Boolean.class))
                .country(getCountry(source))
                .synodCategory(getSynodCategory(source))
                .description(source.get("description", String.class))
                .build();
    }

    private Country getCountry(Row source) {
        Country country = new Country();
        country.setId(source.get("country", Integer.class));
        country.setName(source.get("countryName", String.class));
        country.setIsoCode(source.get("countryIsoCode", String.class));
        return country;
    }

    private SynodCategory getSynodCategory(Row source) {
        return SynodCategory.builder()
                .id(source.get("category", Integer.class))
                .name(source.get("categoryName", String.class))
                .description(source.get("categoryDesc", String.class))
                .build();
    }
}
