package com.jamesorban.ecommerceapplicationbackend.dao.converter.helper;

import com.jamesorban.ecommerceapplicationbackend.models.*;
import io.r2dbc.spi.Row;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;
public final class UserConverterHelper {

    private UserConverterHelper() {

    }

    public static Country getCountry(Row source) {
        Country country = new Country();
        country.setId(source.get("country", Integer.class));
        country.setName(source.get("countryName", String.class));
        country.setIsoCode(source.get("countryIsoCode", String.class));
        return country;
    }

    public static Title getTitle(Row source) {
        return Title.builder()
                .id(source.get("title", Integer.class))
                .name(source.get("titleName", String.class))
                .build();
    }

    public static Role getRole(Row source) {
        return Role.builder()
                .id(source.get("role", Integer.class))
                .name(source.get("roleName", String.class)).build();
    }

    public static OutboundRow getWriteRow(User user) {
        OutboundRow row = new OutboundRow();
        row.put("id", Parameter.from(user.getId()));
        row.put("name", Parameter.from(user.getName()));
        row.put("surname", Parameter.from(user.getSurname()));
        row.put("email", Parameter.from(user.getEmail()));
        row.put("username", Parameter.from(user.getUsername()));
        row.put("password", Parameter.from(user.getPassword()));
        row.put("title", Parameter.from(user.getTitle().getId()));
        row.put("role", Parameter.from(user.getRole().getId()));
        row.put("country", Parameter.from(user.getCountry().getId()));
        return row;
    }
}