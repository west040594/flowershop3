package com.accenture.be.access.user;

import com.accenture.be.entity.user.User;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;


@Repository("userDao")
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<User> findAll() {

        List<User> users = null;
        try {
            TypedQuery<User> query  = entityManager.createNamedQuery("User.findAll", User.class);
            users = query.getResultList();
        } catch (NoResultException nr) {
            //users = new ArrayList<>(0);
            users = Collections.emptyList();
        }
        return users;
    }

    @Override
    public User findById(long userId) {

        User user = null;
        try {
            TypedQuery<User> query  = entityManager.createNamedQuery("User.findById", User.class)
                    .setParameter("id", userId);
            user = query.getSingleResult();

        }catch (NoResultException nr) {

        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            TypedQuery<User> query  = entityManager.createNamedQuery("User.findByUsername", User.class)
                    .setParameter("username", username);
            user = query.getSingleResult();
        } catch (NoResultException nr) {

        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            TypedQuery<User> query  = entityManager.createNamedQuery("User.findByEmail", User.class)
                    .setParameter("email", email);
            user = query.getSingleResult();
        } catch (NoResultException nr) {

        }
        return user;
    }


    @Override
    @Transactional
    public Long save(User user) {
        /*entityManager.persist(user);
        entityManager.flush();*/
        sessionFactory.getCurrentSession().save(user);
        return user.getId();
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(long userId) {
        return false;
    }
}
