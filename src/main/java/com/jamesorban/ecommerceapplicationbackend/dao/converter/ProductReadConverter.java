package com.jamesorban.ecommerceapplicationbackend.dao.converter;




import com.jamesorban.ecommerceapplicationbackend.models.*;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
@ReadingConverter
public class ProductReadConverter implements Converter<Row, Product> {

    @Override
    public Product convert(Row source) {
        return Product.builder()
                .id(source.get("id", Integer.class))
                .name(source.get("name", String.class))
                .description(source.get("description", String.class))
                .company(getCompany(source))
                .productCategory(getCategory(source))
                .color(getColor(source))
                .build();
    }

    private ProductCategory getCategory(Row source) {
        return ProductCategory.builder()
                .id(source.get("category", Integer.class))
                .name(source.get("categoryName", String.class))
                .build();
    }

    private Company getCompany(Row source) {
        return Company.builder()
                .id(source.get("company", Integer.class))
                .name(source.get("companyName", String.class))
                .build();
    }

    private Color getColor(Row source) {
        return Color.builder()
                .id(source.get("color", Integer.class))
                .type(source.get("colorName", String.class))
                .build();
    }
}

