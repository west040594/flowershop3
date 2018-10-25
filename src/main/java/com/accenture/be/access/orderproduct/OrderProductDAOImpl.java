package com.accenture.be.access.orderproduct;

import com.accenture.be.entity.orderproduct.OrderProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("orderProductDao")
public class OrderProductDAOImpl implements OrderProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<OrderProduct> findAll() {
        return null;
    }

    @Override
    public OrderProduct findById(long orderProductId) {
        return null;
    }

    @Override
    public List<OrderProduct> findByOrder(long orderId) {
        return null;
    }

    @Override
    public Long save(OrderProduct orderProduct) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long orderProductId = (Long)session.save(orderProduct);
        session.getTransaction().commit();
        session.close();
        return orderProductId;
    }

    @Override
    public void delete(long orderProductId) {
        return;
    }
}
