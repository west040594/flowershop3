package com.accenture.be.business.user.implement;

import com.accenture.be.access.user.UserDAO;
import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.customer.converters.CustomerConverter;
import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.business.user.converters.UserConverter;
import com.accenture.be.business.user.exceptions.UserException;
import com.accenture.be.business.user.interfaces.UserService;
import com.accenture.be.business.user.validators.LoginUserValidator;
import com.accenture.be.business.user.validators.RegistrationUserValidator;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;
import com.accenture.fe.enums.user.UserRole;
import com.accenture.fe.enums.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RegistrationUserValidator registrationUserValidator;

    @Autowired
    private LoginUserValidator loginUserValidator;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<User> findAllUser() {
        return userDAO.findAll();
    }


    @Transactional
    @Override
    public UserDTO register(UserDTO userDTO) throws UserException {

        //Валидация формы регистрации
        StringBuilder errors = new StringBuilder();
        DataBinder dataBinder = new DataBinder(userDTO);
        dataBinder.addValidators(registrationUserValidator);
        dataBinder.validate();

        //Если валидация не прошла, то выкидываем сформированную строку ошибок на сервлет
        if(dataBinder.getBindingResult().hasErrors()) {
            dataBinder.getBindingResult().getAllErrors().stream().
                    forEach(e -> errors.append(e.getDefaultMessage()).append("<br/>"));

            throw new UserException(errors.toString());
            //Иначе сохраняем пользоватея с присвоиным ему покупателем, и возвращаем его DTO
        } else {
            User user = UserConverter.convertToEntity(userDTO);
            //Устаналвиваем дату роль и статус новому пользователю
            user.setStatus(UserStatus.ACTIVE);
            user.setRole(UserRole.USER);
            user.setCreatedUpdated(new Date(), new Date());

            Customer customer = CustomerConverter.convertToEntity(userDTO.getCustomer());
            //Устанавливаем начальный баланс и скидку покупателю
            customer.setBalance(new BigDecimal(2000));
            customer.setDiscount(3);

            user.setCustomer(customer);
            customer.setUser(user);
            user = saveUser(user);
            return UserConverter.convertToDTO(user);
        }
    }

    @Override
    public UserDTO login(UserDTO userDTO) throws UserException {

        //Валидация формы регистрации
        StringBuilder errors = new StringBuilder();
        DataBinder dataBinder = new DataBinder(userDTO);
        dataBinder.addValidators(loginUserValidator);
        dataBinder.validate();

        //Если валидация не прошла, то выкидываем сформированную строку ошибок на сервлет
        if(dataBinder.getBindingResult().hasErrors()) {
            dataBinder.getBindingResult().getAllErrors().stream().
                    forEach(e -> errors.append(e.getDefaultMessage()).append("<br/>"));

            throw new UserException(errors.toString());
        }

        //Если совпадение в бд по логину или почте не найдены - исключение
        User userFindByEmail = userDAO.findByEmail(userDTO.getEmail());
        User userFindByUsername = userDAO.findByUsername(userDTO.getUsername());
        if(userFindByEmail == null &&  userFindByUsername == null) {
            errors.append("Пользователь с таким логином или email не найден");
            throw new UserException(errors.toString());
        }

        //Определяем найденого пользователя
        User detectedUser = null;
        if(userFindByUsername != null) {
            detectedUser = userFindByUsername;
        } else {
            detectedUser = userFindByEmail;
        }

        if(!detectedUser.getPassword().equals(userDTO.getPassword())) {
            errors.append("Проверьте правильность пароля");
            throw new UserException(errors.toString());
        }

        return UserConverter.convertToDTO(detectedUser);
    }

    @Override
    public void setUserSession(HttpSession session, UserDTO userDTO) {
        int customerDiscount = userDTO.getCustomer().getDiscount();
        userDTO.getCustomer().setCart(new Cart(customerDiscount));
        session.setAttribute("user", userDTO);
    }


    @Override
    public User saveUser(User user) {
        userDAO.save(user);
        return userDAO.findById(user.getId());
    }
}
