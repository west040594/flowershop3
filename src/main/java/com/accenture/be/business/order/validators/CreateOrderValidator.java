package com.accenture.be.business.order.validators;

import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.order.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Валидатор формы заказа
 */
@Service("createOrderValidator")
public class CreateOrderValidator implements Validator {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OrderForm orderForm = (OrderForm) o;

        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty",
                "Имя не должно быть пустым");

        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty",
                "Фамилия не должна быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "phoneNumber.empty",
                "Телефон не должен быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "street", "street.empty",
                "Улица не должна быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "city", "city.empty",
                "Город не должен быть пустой");

        ValidationUtils.rejectIfEmpty(errors, "country", "country.empty",
                "Страна не должна быть пустой");

        //Сравниваем баланс покупателя и общую цену корзины, если денег на счету меньше чем требуется
        //то выкидываем ошибку
        if(customerService.findCustomerById(orderForm.getCustomerId()).getBalance()
                .compareTo(orderForm.getCart().getTotal()) < 0) {
            errors.reject("customer.notEnoughBalance",
                    "Недостаточно средств для совершения платежа");
        }
    }
}
