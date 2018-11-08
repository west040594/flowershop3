package com.accenture.fe.rs;

/**
 * REST Сервис для работы с Пользователем
 */
public interface UserCgService {
    /**
     * Проверяет содержится ли пользователь с указаным именем в бд
     * @param username Имя пользователя
     * @return True, если пользователь с таким именем уже существует
     */
    boolean checkUserExist(String username);
}
