package com.learn.simpleconsoleapp.validators;

import com.learn.simpleconsoleapp.models.Address;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AddressValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "line1", "address.line1.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "address.city.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stateOrProvince", "address.stateOrProvince.empty");
    }
}
