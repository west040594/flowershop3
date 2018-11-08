package com.accenture.be.access.order;

import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.order.Order;
import java.util.List;

public interface OrderDAO {

    /**
     * Возвращает полный список объектов Order
     * @return Лист Order
     */
    List<Order> findAll();

    /**
     * Возвращает конкретный экземпляр Order, представленный указанным ID
     * @param orderId ID Заказа
     * @return Order объект
     */
    Order findById(long orderId);


    /**
     * Возвращает список объектов Order, которые соотвествуют указанному пользователю
     * @param customer Покупатель
     * @return Лист OrderDTO
     */
    List<Order> findByCustomer(Customer customer);


    /**
     * Этот метод попытается вставить Order в репозиторий.
     * @param order Объект Заказа для вставки в репозиторий
     * @return Идентификатор записи в репозитории
     */
    Long save(Order order);


    /**
     * Обновляет существующий Заказ в репозитории со значениями,
     * представленными объектом Order, переданными в качестве параметра.
     * Параметр Order должен содержать идентификатор, соответствующий существующему ID в репозитории,
     * и все поля должны быть заполнены и действительны.
     * @param order Объект Заказа содержащий информацию для обновления
     */
    void update(Order order);


    /**
     * Удаляет существующий Заказ в репозитории, представленный указанным ID
     * @param orderId ID Заказа
     */
    void delete(long orderId);
}
