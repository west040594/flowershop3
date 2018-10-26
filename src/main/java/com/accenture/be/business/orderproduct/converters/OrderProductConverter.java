package com.accenture.be.business.orderproduct.converters;

import com.accenture.be.business.order.converters.OrderConverter;
import com.accenture.be.business.product.converters.ProductConverter;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.fe.dto.orderproduct.OrderProductDTO;

import java.util.ArrayList;
import java.util.List;


public class OrderProductConverter {
    public static OrderProductDTO convertToDTO(OrderProduct orderProductEntity) {
        OrderProductDTO orderProductDTO = null;
        if(orderProductEntity != null) {
            orderProductDTO = new OrderProductDTO(orderProductEntity.getId(),
                    ProductConverter.convertToDTO(orderProductEntity.getProduct()),
                    OrderConverter.convertToDTO(orderProductEntity.getOrder()),
                    orderProductEntity.getQuantity());
        }
        return orderProductDTO;
    }

    public static List<OrderProductDTO> convertToDTO(List<OrderProduct> orderProducts) {
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        for (OrderProduct orderProduct : orderProducts) {
            OrderProductDTO orderProductDTO = OrderProductConverter.convertToDTO(orderProduct);
            if(orderProductDTO != null) {
                orderProductDTOS.add(orderProductDTO);
            }
        }
        return orderProductDTOS;
    }

    public static OrderProduct convertToEntity(OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = null;
        if(orderProductDTO != null) {
            orderProduct = new OrderProduct(
                    ProductConverter.convertToEntity(orderProductDTO.getProduct()),
                    OrderConverter.convertToEntity(orderProductDTO.getOrder()),
                    orderProductDTO.getQuantity());
        }
        return orderProduct;
    }

    public static List<OrderProduct> convertToEntity(List<OrderProductDTO> orderProductDTOS) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDTO orderProductDTO : orderProductDTOS) {
            OrderProduct orderProduct = OrderProductConverter.convertToEntity(orderProductDTO);
            if(orderProduct != null) {
                orderProducts.add(orderProduct);
            }
        }
        return orderProducts;
    }
}
