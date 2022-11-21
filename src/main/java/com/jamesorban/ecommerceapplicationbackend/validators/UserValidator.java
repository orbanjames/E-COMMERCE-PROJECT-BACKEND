package com.jamesorban.ecommerceapplicationbackend.validators;




import com.jamesorban.ecommerceapplicationbackend.models.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$");

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (StringUtils.isBlank(user.getName())) {
            errors.rejectValue("name", "name.empty", "Name is empty");
        }

        if (StringUtils.isBlank(user.getUsername())) {
            errors.rejectValue("Username", " Username.empty", "Username is empty");
        }

        if (StringUtils.isBlank(user.getSurname())) {
            errors.rejectValue("surname", "surname.empty", "Surname is empty");
        }

        if (StringUtils.isBlank(user.getEmail()) || !EMAIL_REGEX.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "email.empty", "Email is not valid");
        }
    }
}
