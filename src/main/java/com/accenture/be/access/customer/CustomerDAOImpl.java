package com.accenture.be.access.customer;

import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Transactional
@Repository("customerDao")
public class CustomerDAOImpl implements CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Customer findById(long customerId) {

        Customer customer = null;
        try {
            TypedQuery<Customer> query  = entityManager.createNamedQuery("Customer.findById", Customer.class)
                    .setParameter("id", customerId);
            customer = query.getSingleResult();

        }catch (NoResultException nr) {

        }
        return customer;
    }

    @Override
    public Customer findByUser(User user) {
        return null;
    }


    @Override
    public Long save(Customer customer)
    {
        Session session = sessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Long customerId = (Long)session.save(customer);
        session.getTransaction().commit();
        session.close();
        return customerId;
    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }
}
