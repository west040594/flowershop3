package com.accenture.be.business.order.validators;

import com.accenture.be.access.order.OrderDAO;
import com.accenture.be.access.user.UserDAO;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("createOrderValidator")
public class CreateOrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OrderDTO order = (OrderDTO) o;

        ValidationUtils.rejectIfEmpty(errors, "customer.firstName", "customer.firstName.empty",
                "Имя не должно быть пустым");

        ValidationUtils.rejectIfEmpty(errors, "customer.lastName", "customer.lastName.empty",
                "Фамилия не должна быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "customer.phoneNumber", "customer.phoneNumber.empty",
                "Телефон не должен быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "customer.street", "customer.street.empty",
                "Улица не должна быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "customer.city", "customer.city.empty",
                "Город не должен быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "customer.country", "customer.country.empty",
                "Страна не должна быть пустой");
    }
}
