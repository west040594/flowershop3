package com.accenture.be.access.orderproduct;

import com.accenture.be.entity.orderproduct.OrderProduct;
import java.util.List;

public interface OrderProductDAO {

    /**
     * Возвращает полный список объектов OrderProduct
     * @return Лист OrderProduct
     */
    List<OrderProduct> findAll();

    /**
     * Возвращает конкретный экземпляр OrderProduct, представленный указанным ID
     * @param orderProductId ID OrderProduct
     * @return OrderProduct объект
     */
    OrderProduct findById(long orderProductId);

    /**
     * Возвращает список объектов OrderProduct, которые содержат указанный ID Заказа
     * @param orderId ID Заказа
     * @return Лист OrderProduct
     */
    List<OrderProduct> findByOrder(long orderId);


    /**
     * Этот метод попытается вставить OrderProduct в репозиторий.
     * @param orderProduct Объект OrderProduct для вставки в репозиторий
     * @return Идентификатор записи в репозитории
     */
    Long save(OrderProduct orderProduct);


    /**
     * Удаляет существующий OrderProduct в репозитории, представленный указанным ID
     * @param orderProductId ID OrderProduct
     */
    void delete(long orderProductId);
}
