package com.accenture.be.business.user.validators;

import com.accenture.be.access.user.UserDAO;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("registrationFormValidator")
public class RegistrationUserValidator implements Validator {

    @Autowired
    protected UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserDTO user = (UserDTO) o;

        ValidationUtils.rejectIfEmpty(errors, "customer.firstName", "customer.firstName.empty",
                "Имя не должно быть пустым");

        ValidationUtils.rejectIfEmpty(errors, "customer.lastName", "customer.lastName.empty",
                "Фамилия не должна быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "username", "user.username.empty",
                "Логин не должен быть пустым");


        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty",
                "Email не должен быть пустым");


        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty",
                "Пароль не должен быть пустым");

        if(userDAO.findByUsername(user.getUsername()) != null) {
            errors.reject("user.username.isBusy", "Пользователь с данным логином уже зарегестрирован");
        }

        if(userDAO.findByEmail(user.getEmail())!= null) {
            errors.reject("user.email.isBusy", "Пользователь с данным email уже зарегестрирован");
        }


        if(!user.getPassword().equals(user.getConfirmPassword())) {
            errors.reject("user.confirmPassword.notMatch",
                    "Пароли не совпадают");
        }


    }
}
