package com.jamesorban.ecommerceapplicationbackend.dao.converter;



import com.jamesorban.ecommerceapplicationbackend.models.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class ProductWriteConverter implements Converter<Product, OutboundRow> {

    @Override
    public OutboundRow convert(Product product) {
        OutboundRow row = new OutboundRow();

        row.put("id", Parameter.from(product.getId()));
        row.put("name", Parameter.from(product.getName()));
        row.put("description", Parameter.from(product.getDescription()));
        row.put("price", Parameter.from(product.getPrice()));
        row.put("company", Parameter.from(product.getCompany().getId()));
        row.put("category", Parameter.from(product.getProductCategory().getId()));
        row.put("color", Parameter.from(product.getColor().getId()));
        return row;
    }
}
