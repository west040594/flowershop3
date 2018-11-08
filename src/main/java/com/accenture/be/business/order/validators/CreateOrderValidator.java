package com.accenture.be.business.order.validators;

import com.accenture.fe.dto.order.OrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Валидатор формы заказа
 */
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

        //Сравниваем баланс покупателя и общую цену корзины, если денег на счету меньше чем требуется
        //то выкидываем ошибку
        if(order.getCustomer().getBalance().compareTo(order.getCustomer().getCart().getTotal()) < 0) {
            errors.reject("customer.notEnoughBalance",
                    "Недостаточно средств для совершения платежа");
        }
    }
}
