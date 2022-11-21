package com.jamesorban.ecommerceapplicationbackend.dao.converter.helper;

import com.jamesorban.ecommerceapplicationbackend.models.*;
import io.r2dbc.spi.Row;

import java.time.LocalDate;

public final class ConverterHelper {

    private ConverterHelper() {
    }

    public static final String SYNOD_ID = "synodId";
    public static final String SYNOD_NAME = "synodName";
    public static final String SYNOD_DATE = "synodDate";
    public static final String SYNOD_CLOSED = "synodIsClosed";
    public static final String SYNOD_TOWN = "synodTown";
    public static final String SYNOD_CATEGORY = "synodCategory";
    public static final String SYNOD_COUNTRY = "synodCountry";

    public static Synod getSynod(Row source) {
        return Synod.builder()
                .id(source.get(SYNOD_ID, Integer.class))
                .name(source.get(SYNOD_NAME, String.class))
                .date(source.get(SYNOD_DATE, LocalDate.class))
                .town(source.get(SYNOD_TOWN, String.class))
                .isClosed(source.get(SYNOD_CLOSED, Boolean.class))
                .synodCategory(getSynodCategory(source))
                .country(getCountry(source, "conference"))
                .description(source.get("description", String.class))
                .build();
    }

    public static User getUser(Row source, String prefix) {
        return User.builder()
                .id(source.get(prefix + "user", Integer.class))
                .name(source.get(prefix + "userName", String.class))
                .surname(source.get(prefix + "userSurname", String.class))
                .email(source.get(prefix + "userEmail", String.class))
                .username(source.get(prefix + "userUsername", String.class))
                .password(source.get(prefix + "userPassword", String.class))
                .country(getCountry(source, prefix + "user"))
                .title(getTitle(source, prefix))
                .role(getRole(source, prefix))
                .build();
    }

    private static Country getCountry(Row source, String type) {
        return Country.builder().id(source.get(type + "Country", Integer.class)).build();
    }

    private static Title getTitle(Row source, String prefix) {
        return Title.builder().id(source.get(prefix + "userTitle", Integer.class)).build();
    }

    private static Role getRole(Row source, String prefix) {
        return Role.builder().id(source.get(prefix + "userRole", Integer.class)).build();
    }

    private static SynodCategory getSynodCategory(Row source) {
        return SynodCategory.builder().id(source.get(SYNOD_CATEGORY, Integer.class)).build();
    }
}
