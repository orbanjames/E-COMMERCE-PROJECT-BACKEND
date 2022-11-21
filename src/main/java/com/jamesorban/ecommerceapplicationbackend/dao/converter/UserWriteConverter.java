package com.jamesorban.ecommerceapplicationbackend.dao.converter;


import com.jamesorban.ecommerceapplicationbackend.dao.converter.helper.UserConverterHelper;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;


@WritingConverter
public class UserWriteConverter implements Converter<User, OutboundRow> {

    @Override
    public OutboundRow convert(User user) {
        return UserConverterHelper.getWriteRow(user);
    }
}

