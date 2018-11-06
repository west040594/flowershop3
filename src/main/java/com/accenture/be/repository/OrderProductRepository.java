package com.accenture.be.repository;

import com.accenture.be.entity.orderproduct.OrderProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
    List<OrderProduct> findByOrder_id(long orderId);
}
