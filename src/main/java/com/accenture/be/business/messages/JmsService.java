package com.accenture.be.business.messages;

/**
 * Сервис для работы с JMS
 */
public interface JmsService {
    /**
     * Установить необходимые параметры для работы с jms
     * connection, session, queue, producer, consumer,
     */
    void initialize();

    /**
     * Закрыть соединение connection
     */
    void destroy();

    /**
     * Отправить новое сообщение в очередь activemq
     * @param text Тело сообщения
     */
    void sentMessage(String text);
}
