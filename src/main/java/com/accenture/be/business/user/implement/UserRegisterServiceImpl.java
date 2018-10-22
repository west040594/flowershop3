package com.accenture.be.business.user.implement;

import com.accenture.be.business.user.interfaces.UserRegisterService;
import com.accenture.be.business.user.exceptions.UserRegisterException;
import com.accenture.be.business.user.validators.UserRegistrationFormValidator;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import com.accenture.fe.enums.user.UserRole;
import com.accenture.fe.enums.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;

import java.math.BigDecimal;
import java.util.Date;


@Service("userRegisterService")
public class UserRegisterServiceImpl extends UserServiceImpl implements UserRegisterService {

    @Autowired
    private UserRegistrationFormValidator userRegistrationFormValidator;

    @Override
    public User register(String firstName, String lastName, String username,
                         String email, String password, String passwordConfirm)
    throws UserRegisterException {

        //Формируем Пользователя и соотвествующего ему Покупателя
        //Покупателю назначается стартовый бонус в размере 2000 и скидка 0%
        User user = new User(
                username, password, email,
                UserStatus.ACTIVE, UserRole.USER,
                new Date(), new Date()
        );
        Customer customer = new Customer(
                firstName, lastName, new BigDecimal(2000), 0, user);


        //Валидация
        StringBuilder errors = new StringBuilder();
        DataBinder dataBinder = new DataBinder(user);
        dataBinder.addValidators(userRegistrationFormValidator);
        dataBinder.validate();

        //Если валидация не прошла, то выкидываем сформированную строку ошибок на сервлет
        if(dataBinder.getBindingResult().hasErrors()) {
            dataBinder.getBindingResult().getAllErrors().stream().
                    forEach(e -> errors.append(e.getDefaultMessage()));
            throw new UserRegisterException(errors.toString());
        } else {
            return saveUserWithCustomer(user, customer);
        }
    }

}
