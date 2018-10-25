package com.accenture.be.business.order.converters;

import com.accenture.be.business.customer.converters.CustomerConverter;
import com.accenture.be.entity.order.Order;
import com.accenture.fe.dto.order.OrderDTO;

public class OrderConverter {

    public static OrderDTO convertToDTO(Order orderEntity) {
        OrderDTO orderDTO = null;
        if(orderEntity != null) {
            orderDTO = new OrderDTO(orderEntity.getId(),
                    CustomerConverter.convertToDTO(orderEntity.getCustomer()),
                    orderEntity.getTotal(), orderEntity.getDeliveryAddress(), orderEntity.getStatus(),
                    orderEntity.getCreatedAt(), orderEntity.getClosetAt());
        }
        return orderDTO;
    }

    public static Order convertToEntity(OrderDTO orderDTO) {
        Order orderEntity = null;
        if(orderDTO != null) {
            orderEntity = new Order(CustomerConverter.convertToEntity(orderDTO.getCustomer()),
                    orderDTO.getTotal(), orderDTO.getDeliveryAddress(), orderDTO.getStatus(),
                    orderDTO.getCreatedAt(), orderDTO.getClosetAt()
            );
        }
        return orderEntity;
    }
}
