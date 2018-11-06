package com.accenture.be.business.user.implement;

import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.messages.JmsService;
import com.accenture.be.business.user.exceptions.UserException;
import com.accenture.be.business.user.interfaces.UserMarshgallingService;
import com.accenture.be.business.user.interfaces.UserService;
import com.accenture.be.business.user.validators.LoginUserValidator;
import com.accenture.be.business.user.validators.RegistrationUserValidator;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import com.accenture.be.repository.UserRepository;
import com.accenture.fe.dto.user.UserDTO;
import com.accenture.fe.enums.user.UserRole;
import com.accenture.fe.enums.user.UserStatus;
import org.apache.cxf.helpers.IOUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private UserMarshgallingService userMarshgallingService;

    @Autowired
    private JmsService jmsService;

    @Autowired
    private RegistrationUserValidator registrationUserValidator;

    @Autowired
    private LoginUserValidator loginUserValidator;

    @Autowired
    private Mapper mapper;

    @Override
    public List<User> findAllUser() {
        return (List<User>) userDAO.findAll();
    }


    @Transactional
    @Override
    public User register(UserDTO userDTO) throws UserException {

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
            User user = mapper.map(userDTO, User.class);
            //Устаналвиваем дату роль и статус новому пользователю
            user.setStatus(UserStatus.ACTIVE);
            user.setRole(UserRole.USER);
            user.setCreatedUpdated(new Date(), new Date());

            Customer customer = mapper.map(userDTO.getCustomer(), Customer.class);
            //Устанавливаем начальный баланс и скидку покупателю
            customer.setBalance(new BigDecimal(2000));
            customer.setDiscount(3);

            user.setCustomer(customer);
            customer.setUser(user);
            user = saveUser(user);
            createUserXmlAndSent(user);
            log.debug("User with id = {}, username = {}, email = {} was registered",
                    user.getId(), user.getUsername(), user.getEmail());
            return user;
        }
    }

    @Override
    public User login(UserDTO userDTO) throws UserException {

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

        log.debug("User with id = {}, username = {}, was entered",
                detectedUser.getId(), detectedUser.getUsername());
        return detectedUser;
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
        return userDAO.findById(user.getId()).get();
    }

    @Override
    public void createUserXmlAndSent(User user) {
        try {
            //Загружаем проперти
            Properties properties = new Properties();
            InputStream propertyInputStream =
                    getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(propertyInputStream);
            //Создаем xml файл
            userMarshgallingService.convertFromUserToXML(user, properties.getProperty("user.xml.path"));
            //Читаем xml файл и отправляем на почту
            FileInputStream xmlInputStream = new FileInputStream(properties.getProperty("user.xml.path"));
            String message = IOUtils.toString(xmlInputStream, "UTF-8");
            jmsService.sentMessage(message);
            //Закрываем соединения
            propertyInputStream.close();
            xmlInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
