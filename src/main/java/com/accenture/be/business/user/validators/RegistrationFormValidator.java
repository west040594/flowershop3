package com.accenture.be.business.user.validators;

import com.accenture.be.business.user.forms.RegistrationForm;
import com.accenture.be.entity.user.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("userRegistrationFormValidator")
public class UserRegistrationFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RegistrationForm registrationForm = (RegistrationForm)o;

        ValidationUtils.rejectIfEmpty(errors, "user.username", "user.username.empty",
                "Логин не должен быть пустым");

        ValidationUtils.rejectIfEmpty(errors, "user.email", "user.email.empty",
                "Email не должен быть пустым");


        ValidationUtils.rejectIfEmpty(errors, "user.password", "user.password.empty",
                "Пароль не должен быть пустым");

    }
}
