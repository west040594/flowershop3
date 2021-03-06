package com.accenture.be.business.user.interfaces;

import com.accenture.be.business.user.exceptions.UserException;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.LoginForm;
import com.accenture.fe.dto.user.RegisterForm;
import com.accenture.fe.dto.user.UserDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Сервис для работы с Пользователями
 */
public interface UserService {

    /**
     * Этот метод найдет пользователя, которому соотвествует указанный Id
     * @param userId Id Пользователя
     * @return Объект User
     */
    User findUserById(long userId);

    /**
     * Этот метод находит всех существующих пользователей
     * @return Колекция пользователей
     */
    List<User> findAllUser();

    /**
     * Этот метод сохранит нового пользователя
     * @param user Новый пользователь
     * @return Сохраненный пользователь
     */
    User saveUser(User user);

    /**
     * Этот метод авторизирует существующего пользователя в системе
     * Так же провалидирует форму входа по объекту LoginForm
     * @param loginForm LoginForm объект
     * @return Авторизированный пользователь
     * @throws UserException
     */
    User login(LoginForm loginForm) throws UserException;

    /**
     * Этот метод зарегистрирует нового пользователя в системе
     * Так же провалидиует форму регистрации по объекту RegisterForm
     * @param registerForm RegisterForm объект
     * @return Новый зарегистрированный пользователь
     * @throws UserException
     */
    User register(RegisterForm registerForm) throws UserException;

    /**
     * Устанавливает авторизированного пользователя в сессию
     * @param session Объект сессии
     * @param userDTO Объект UserDTO
     */
    void setUserSession(HttpSession session, UserDTO userDTO);

    /**
     *  Метод кооторый создаст, сохранит, и отправит сообщение в формате xml,
     *  которое представлено данным пользователем
     * @param user Объект User для конвертации в xml
     */
    void createUserXmlAndSent(User user);

}
