package com.accenture.be.access.user;

import com.accenture.be.entity.user.User;
import java.util.List;

public interface UserDAO {

    /**
     * Возвращает полный список объектов User
     * @return Лист User
     */
    List<User> findAll();

    /**
     * Возвращает конкретный экземпляр User, представленный указанным ID
     * @param userId ID Пользователя
     * @return User объект
     */
    User findById(long userId);

    /**
     * Возвращает конкретный экземпляр User, представленный указанным Именем
     * @param username Логин Пользователя
     * @return User
     */
    User findByUsername(String username);

    /**
     * Возвращает конкретный экземпляр User, представленный указанным Email
     * @param email Email Пользователя
     * @return User
     */
    User findByEmail(String email);

    /**
     * Этот метод попытается вставить User в репозиторий.
     * @param user Объект Пользователя для вставки в репозиторий
     * @return Идентификатор записи в репозитории
     */
    Long save(User user);

    /**
     * Обновляет существующего Пользователя в репозитории со значениями,
     * представленными объектом User, переданными в качестве параметра.
     * Параметр User должен содержать идентификатор, соответствующий существующему ID в репозитории,
     * и все поля должны быть заполнены и действительны.
     * @param user Объект Пользователя содержащий информацию для обновления
     */
    void update(User user);

    /**
     * Удаляет существующуего Пользователя в репозитории, представленный указанным ID
     * @param userId ID Пользователя
     */
    void delete(long userId);
}
