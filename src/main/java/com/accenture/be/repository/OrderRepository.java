package com.accenture.be.repository;

import com.accenture.be.entity.order.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для работы с сущностями Order
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

}
