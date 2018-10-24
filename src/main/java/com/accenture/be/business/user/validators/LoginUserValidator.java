package com.accenture.be.business.user.validators;

import com.accenture.be.access.user.UserDAO;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("loginFormValidator")
public class LoginUserValidator implements Validator {

    @Autowired
    protected UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;


        ValidationUtils.rejectIfEmpty(errors, "username", "user.username.empty",
                "Поле Логин/Email не должно быть пустым");

        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty",
                "Пароль не должен быть пустым");


    }
}
