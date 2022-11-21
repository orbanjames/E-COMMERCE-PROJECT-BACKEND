package com.jamesorban.ecommerceapplicationbackend.dao.converter;

import com.jamesorban.ecommerceapplicationbackend.dao.converter.helper.UserConverterHelper;
import com.jamesorban.ecommerceapplicationbackend.models.User;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class UserReadConverter implements Converter<Row, User> {

    @Override
    public User convert(Row source) {
        return User.builder()
                .id(source.get("id", Integer.class))
                .name(source.get("name", String.class))
                .surname(source.get("surname", String.class))
                .email(source.get("email", String.class))
                .username(source.get("username", String.class))
                .password(source.get("password", String.class))
                .country(UserConverterHelper.getCountry(source))
                .role(UserConverterHelper.getRole(source))
                .title(UserConverterHelper.getTitle(source))
                .build();
    }
}

