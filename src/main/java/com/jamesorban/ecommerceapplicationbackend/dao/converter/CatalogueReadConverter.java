package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.dao.converter.helper.ConverterHelper;
import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import com.jamesorban.ecommerceapplicationbackend.models.CatalogueCategory;
import com.jamesorban.ecommerceapplicationbackend.models.SynodSection;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDate;

@ReadingConverter
public class CatalogueReadConverter implements Converter<Row, Catalogue> {

    @Override
    public Catalogue convert(Row source) {
        return Catalogue.builder()
                .id(source.get("id", Integer.class))
                .name(source.get("name", String.class))
                .description(source.get("description", String.class))
                .createdAt(source.get("createdAt", LocalDate.class))
                .file(source.get("file", byte[].class))
                .fileName(source.get("fileName", String.class))
                .user(ConverterHelper.getUser(source,""))
                .synod(ConverterHelper.getSynod(source))
                .category(getCatalogueCategory(source))
                .section(SynodSection.builder().id(source.get("section", Integer.class)).build())
                .build();
    }

    private CatalogueCategory getCatalogueCategory(Row source) {
        return CatalogueCategory.builder()
                .id(source.get("category", Integer.class))
                .name(source.get("categoryName", String.class))
                .description(source.get("categoryDescription", String.class))
                .build();
    }
}
