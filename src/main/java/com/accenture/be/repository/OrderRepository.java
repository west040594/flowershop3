package com.accenture.be.repository;

import com.accenture.be.entity.order.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
