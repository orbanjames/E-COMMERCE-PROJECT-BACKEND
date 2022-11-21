package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.models.Cart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class CartWriteConverter implements Converter<Cart, OutboundRow> {

    @Override
    public OutboundRow convert(Cart source) {
        OutboundRow row = new OutboundRow();

        row.put("id", Parameter.from(source.getId()));
        row.put("product", Parameter.from(source.getProduct()));
        row.put("quantity", Parameter.from(source.getQuantity()));
        row.put("price", Parameter.from(source.getPrice()));
        row.put("subtotal", Parameter.from(source.getSubtotal()));
        return row;
    }

}