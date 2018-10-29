package com.accenture.be.business.orderproduct.interfaces;

import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.orderproduct.OrderProductDTO;

import java.util.List;

public interface OrderProductService {
    List<OrderProduct> findOrderProductByOrder(Order order);
    void saveOrderProducts(List<OrderProduct> orderProducts);
}
