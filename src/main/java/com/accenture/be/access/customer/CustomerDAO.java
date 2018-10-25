package com.accenture.be.access.customer;

import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;

public interface CustomerDAO {

    /**
     * Возвращает конкретный экземпляр Customer, представленный указанным ID
     * @param customerId ID Адреса
     * @return Customer объект
     */
    Customer findById(long customerId);

    /**
     * Возвращает конкретный экземпляр Customer, который принадлежит указанному пользователю
     * @param user Пользоватеь
     * @return Customer объект
     */
    Customer findByUser(User user);

    /**
     * Этот метод попытается вставить Customer в репозиторий
     * @param customer Объект Customer для вставки в репозиторий
     * @return Идентификатор записи в репозитории
     */
    Long save(Customer customer);

    /**
     * Обновляет существующего Покупателя в репозитории со значениями,
     * представленными объектом Customer, переданными в качестве параметра.
     * Параметр Customer должен содержать идентификатор,
     * соответствующий существующему ID в репозитории,
     * и все поля должны быть заполнены и действительны.
     * @param customer Объект Покупателя содержащий информацию для обновления
     * @return true, если строка была обновлена.
     */
    void update(Customer customer);

}
