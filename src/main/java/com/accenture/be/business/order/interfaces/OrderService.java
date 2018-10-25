package com.accenture.be.business.order.interfaces;

import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.entity.order.Order;
import com.accenture.fe.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrder();
    Order saveOrder(Order order);
    Order createOrder(OrderDTO orderDTO, Cart cart) throws OrderException;
}
