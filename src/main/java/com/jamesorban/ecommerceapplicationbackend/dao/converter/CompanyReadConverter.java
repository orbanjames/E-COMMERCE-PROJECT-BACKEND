package com.jamesorban.ecommerceapplicationbackend.dao.converter;



import com.jamesorban.ecommerceapplicationbackend.models.Company;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class CompanyReadConverter implements Converter<Row, Company> {

    @Override
    public Company convert(Row source) {
        return Company.builder()
                .id(source.get("id", Integer.class))
                .name((source.get("name", String.class)))
                .build();
    }
}
