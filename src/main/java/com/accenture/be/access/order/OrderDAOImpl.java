package com.accenture.be.access.order;

import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.order.Order;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository("orderDao")
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> findAll() {

        List<Order> orders = null;
        try {
            TypedQuery<Order> query  = entityManager.createNamedQuery("Order.findAll", Order.class);
            orders = query.getResultList();
        } catch (NoResultException nr) {
            orders = Collections.emptyList();
        }
        return orders;
    }

    @Override
    public Order findById(long orderId) {

        Order order = null;
        try {
            TypedQuery<Order> query  = entityManager.createNamedQuery("Order.findById", Order.class)
                    .setParameter("id", orderId);
            order = query.getSingleResult();

        }catch (NoResultException nr) {
            nr.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Long save(Order order) {
        entityManager.persist(order);
        return order.getId();
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Override
    public void delete(long orderId) {
        return;
    }
}
