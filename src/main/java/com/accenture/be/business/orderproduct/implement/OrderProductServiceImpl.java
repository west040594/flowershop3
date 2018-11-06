package com.accenture.be.business.orderproduct.implement;

import com.accenture.be.business.orderproduct.interfaces.OrderProductService;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.be.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("orderProductService")
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductDAO;

    @Override
    public List<OrderProduct> findOrderProductByOrder(Order order) {
        return orderProductDAO.findByOrder_id(order.getId());
    }

    @Transactional
    @Override
    public void saveOrderProducts(List<OrderProduct> orderProducts) {
        for (OrderProduct orderProduct : orderProducts) {
            orderProductDAO.save(orderProduct);
        }
    }
}
