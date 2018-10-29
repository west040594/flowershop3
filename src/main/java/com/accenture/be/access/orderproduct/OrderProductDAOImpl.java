package com.accenture.be.access.orderproduct;

import com.accenture.be.entity.orderproduct.OrderProduct;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository("orderProductDao")
public class OrderProductDAOImpl implements OrderProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /*@Autowired
    private SessionFactory sessionFactory;*/

    @Override
    public List<OrderProduct> findAll() {
        List<OrderProduct> orderProducts = null;
        try {
            TypedQuery<OrderProduct> query  = entityManager.createNamedQuery("OrderProduct.findAll", OrderProduct.class);
            orderProducts = query.getResultList();
        } catch (NoResultException nr) {
            orderProducts = Collections.emptyList();
        }
        return orderProducts;
    }

    @Override
    public OrderProduct findById(long orderProductId) {
        return null;
    }

    @Override
    public List<OrderProduct> findByOrder(long orderId) {
        List<OrderProduct> orderProducts = null;
        try {
            TypedQuery<OrderProduct> query  = entityManager.createNamedQuery("OrderProduct.findByOrder", OrderProduct.class)
                    .setParameter("orderId", orderId);
            orderProducts = query.getResultList();
        } catch (NoResultException nr) {
            orderProducts = Collections.emptyList();
        }
        return orderProducts;
    }

    @Override
    public Long save(OrderProduct orderProduct) {
        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long orderProductId = (Long)session.save(orderProduct);
        session.getTransaction().commit();
        session.close();
        return orderProductId;*/
        entityManager.persist(orderProduct);
        return orderProduct.getId();
    }

    @Override
    public void delete(long orderProductId) {
        return;
    }
}
