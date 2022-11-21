package com.jamesorban.ecommerceapplicationbackend.validators;

import com.jamesorban.ecommerceapplicationbackend.models.Catalogue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class CatalogueValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Catalogue catalogue = (Catalogue) target;

        if (StringUtils.isBlank(catalogue.getName())) {
            errors.rejectValue("name", "name.empty", "Name is empty");
        }

        if (StringUtils.isBlank(catalogue.getDescription())) {
            errors.rejectValue("description", "description.empty", "Description is empty");
        }

        if (Objects.isNull(catalogue.getUser())) {
            errors.rejectValue("user", "user.empty", "System error: User is empty.");
        }

        if (Objects.isNull(catalogue.getSynod())) {
            errors.rejectValue("synod", "synod.empty", "System error: synod is empty.");
        }
    }
}
