package com.accenture.be.repository;

import com.accenture.be.entity.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

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
}
