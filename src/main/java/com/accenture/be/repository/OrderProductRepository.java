package com.accenture.be.repository;

import com.accenture.be.entity.orderproduct.OrderProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Репозиторий для работы с сущностями OrderProduct
 */
public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
    /**
     * Этот метод найдет все OrderProduct которые соотвествуют указаному id Заказа
     * @param orderId Id Заказа
     * @return Колекция OrderProduct
     */
    List<OrderProduct> findByOrder_id(long orderId);
}
