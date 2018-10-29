package com.accenture.be.business.orderproduct.implement;

import com.accenture.be.access.orderproduct.OrderProductDAO;
import com.accenture.be.business.orderproduct.converters.OrderProductConverter;
import com.accenture.be.business.orderproduct.interfaces.OrderProductService;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.orderproduct.OrderProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("orderProductService")
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Override
    public List<OrderProduct> findOrderProductByOrder(Order order) {
        return orderProductDAO.findByOrder(order.getId());
    }

    @Transactional
    @Override
    public void saveOrderProducts(List<OrderProduct> orderProducts) {
        for (OrderProduct orderProduct : orderProducts) {
            orderProductDAO.save(orderProduct);
        }
    }
}
