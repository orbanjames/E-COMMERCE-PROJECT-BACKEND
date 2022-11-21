package com.jamesorban.ecommerceapplicationbackend.validators;


import com.jamesorban.ecommerceapplicationbackend.models.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if (StringUtils.isBlank(product.getName())) {
            errors.rejectValue("name", "name.empty", "Name is empty");
        }

        if (StringUtils.isBlank(product.getDescription())) {
            errors.rejectValue("description", "description.empty", "Description is empty");
        }

        if (Objects.isNull(product.getPrice())) {
            errors.rejectValue("price", "price.empty", "System error: Price is empty.");
        }

        if (Objects.isNull(product.getProductCategory())) {
            errors.rejectValue("category", "category.empty", "System error: category is empty.");
        }

        if (Objects.isNull(product.getCompany())) {
            errors.rejectValue("company", "company.empty", "System error: company is empty.");
        }

        if (Objects.isNull(product.getColor())) {
            errors.rejectValue("color", "color.empty", "System error: color is empty.");
        }
    }
}

