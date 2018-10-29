package com.accenture.be.business.order.interfaces;

import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.order.Order;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.user.UserDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findAllOrder();
    Order saveOrder(Order order);
    OrderDTO createOrder(OrderDTO orderDTO) throws OrderException;
    Order getOrderById(long orderId);
    void changerOrderStatusToPaid(UserDTO userDTO, Long orderId);
    String formDeliveryAddress(Customer customer);
}
