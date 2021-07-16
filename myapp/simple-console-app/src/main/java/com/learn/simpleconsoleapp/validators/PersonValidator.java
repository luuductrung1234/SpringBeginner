package com.learn.simpleconsoleapp.validators;

import com.learn.simpleconsoleapp.models.Address;
import com.learn.simpleconsoleapp.models.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {

    private final Validator addressValidator;

    /**
     * reuse the logic to validate rich object {@link Person}'s address
     * @param addressValidator
     */
    public PersonValidator(Validator addressValidator) {
        if (addressValidator == null) {
            throw new IllegalArgumentException("The supplied [Validator] is " +
                    "required and must not be null.");
        }
        if (!addressValidator.supports(Address.class)) {
            throw new IllegalArgumentException("The supplied [Validator] must " +
                    "support the validation of [Address] instances.");
        }
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identityNumber", "identityNumber.empty");

        var person = (Person) target;

        if (person.getAge() < 0) {
            errors.rejectValue("age", "negative.value");
        } else if (person.getAge() > 200) {
            errors.rejectValue("age", "too.darn.old");
        }

        try {
            errors.pushNestedPath("address");
            ValidationUtils.invokeValidator(this.addressValidator, person.getAddress(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}
