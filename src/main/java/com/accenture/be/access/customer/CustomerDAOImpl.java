package com.accenture.be.access.customer;

import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return null;
    }

    @Override
    public Customer findByUser(User user) {
        return null;
    }


    @Override
    public Long save(Customer customer)
    {
        sessionFactory.getCurrentSession().save(customer);
        return customer.getId();
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }
}
