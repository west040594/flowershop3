package com.accenture.be.business.user.validators;

import com.accenture.be.entity.user.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("userRegistrationFormValidator")
public class UserRegistrationFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User)o;
        ValidationUtils.rejectIfEmpty(errors, "username", "username.empty",
                "Имя не должно быть пустым");
    }
}
