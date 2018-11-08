package com.accenture.be.business.user.validators;

import com.accenture.be.repository.UserRepository;
import com.accenture.fe.dto.user.RegisterForm;
import com.accenture.fe.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Валидатор формы регистрации
 */
@Component("registrationFormValidator")
public class RegistrationUserValidator implements Validator {

    @Autowired
    private UserRepository userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RegisterForm registerForm = (RegisterForm) o;

        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty",
                "Имя не должно быть пустым");

        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty",
                "Фамилия не должна быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "username", "username.empty",
                "Логин не должен быть пустым");

        ValidationUtils.rejectIfEmpty(errors, "email", "email.empty",
                "Email не должен быть пустым");

        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty",
                "Пароль не должен быть пустым");

        if(userDAO.findByUsername(registerForm.getUsername()) != null) {
            errors.reject("username.isBusy", "Пользователь с данным логином уже зарегестрирован");
        }

        if(userDAO.findByEmail(registerForm.getEmail())!= null) {
            errors.reject("email.isBusy", "Пользователь с данным email уже зарегестрирован");
        }

        if(!registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
            errors.reject("confirmPassword.notMatch",
                    "Пароли не совпадают");
        }

    }
}
