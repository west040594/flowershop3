package com.accenture.be.business.order.interfaces;

import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.order.Order;
import com.accenture.fe.dto.order.OrderDTO;
import java.util.List;

/**
 * Сервис для взаимодействия с заказами
 */
public interface OrderService {

    /**
     * Этот метод найдет все существующие заказы
     * @return Колекци Order
     */
    List<Order> findAllOrder();

    /**
     * Этот метод сохранит новый заказ
     * @param order Новый заказ
     * @return Сохраненный заказ
     */
    Order saveOrder(Order order);

    /**
     * Этот метод создаст новый заказ и провалидирует данные в объекте orderDTO
     * @param orderDTO объект orderDTO с сохраннеными полями из формы
     * @return Новый заказ
     * @throws OrderException
     */
    Order createOrder(OrderDTO orderDTO) throws OrderException;

    /**
     * Этот метод найдет заказ по соответсвующему ему Id
     * @param orderId Id Заказа
     * @return Найденный заказ
     */
    Order getOrderById(long orderId);

    /**
     * Этот метод изменит статус заказа на - Оплачено.
     * Изменит(уменьшит) количество продуктов на складе, которые соотвествую данному заказу
     * Снимет деньги с баланса покупателя
     * @param orderId id Заказа
     * @return Измененный заказ
     */
    Order changerOrderStatusToPaid(Long orderId);

    /**
     * Этот метод изменит статус заказа на - Закрыт и назначет дату закрытия заказа
     * @param orderId id Заказа
     * @return Измененный заказ
     */
    Order changeOrderStatusToClosed(Long orderId);

    /**
     * Этот метод сформирует строку адреса доставки заказа из данных покупателя
     * @param customer Объект Покупателя
     * @return Новая сформированная строка Адреса доставки заказа
     */
    String formDeliveryAddress(Customer customer);
}
