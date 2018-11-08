package com.accenture.be.business.user.interfaces;

import com.accenture.be.entity.user.User;

import java.io.IOException;

/**
 * Сервис для конвертирования объекта User в xml и обратно
 */
public interface UserMarshallingService {
    /**
     * Этот метод конвертирует объект User в xml и сохраняет его
     * @param user Объект User
     * @param filepath Путь для сохрания xml файла
     * @throws IOException
     */
    void convertFromUserToXML(User user, String filepath) throws IOException;

    /**
     * Этот метод конвертирует xml в объект User и возвращает его
     * @param xmlFile xml файл
     * @return Объект User
     * @throws IOException
     */
    User convertFromXMLToUser(String xmlFile) throws IOException;
}
