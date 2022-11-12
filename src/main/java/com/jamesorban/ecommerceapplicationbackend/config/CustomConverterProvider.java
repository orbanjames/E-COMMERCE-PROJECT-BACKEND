package com.jamesorban.ecommerceapplicationbackend.config;


import com.jamesorban.ecommerceapplicationbackend.dao.converter.*;

import java.util.ArrayList;
import java.util.List;

public final class CustomConverterProvider {

    private CustomConverterProvider() {

    }

    public static List<Object> getConverters() {
        List<Object> converterList = new ArrayList<>();
        converterList.add(new UserReadConverter());
        converterList.add(new UserWriteConverter());
        converterList.add(new CategoryReadConverter());
        converterList.add(new CategoryWriteConverter());
        converterList.add(new ProductReadConverter());
        converterList.add(new ProductWriteConverter());
        converterList.add(new CartReadConverter());
        converterList.add(new CartWriteConverter());
        converterList.add(new CompanyReadConverter());
        converterList.add(new CompanyWriteConverter());
        converterList.add(new ColorReadConverter());
        converterList.add(new ColorWriteConverter());
        converterList.add(new UserReadConverter());
        converterList.add(new UserWriteConverter());
        converterList.add(new SynodReadConverter());
        converterList.add(new SynodWriteConverter());
        converterList.add(new CatalogueReadConverter());
        converterList.add(new CatalogueWriteConverter());
        converterList.add(new ApplicantReadConverter());
        converterList.add(new ApplicantWriteConverter());
        converterList.add(new ReviewReadConverter());
        converterList.add(new ReviewWriteConverter());
        converterList.add(new ApplicationReportReadConverter());
        converterList.add(new ApplicationReportWriteConverter());
        converterList.add(new SectionReadConverter());
        converterList.add(new SectionWriteConverter());
        converterList.add(new CatalogueCategoryReadConverter());
        converterList.add(new CatalogueCategoryWriteConverter());
        converterList.add(new CountryReadConverter());
        return converterList;
    }
}
