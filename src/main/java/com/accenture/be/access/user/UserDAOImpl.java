package com.accenture.be.access.user;

import com.accenture.be.entity.user.User;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository("userDao")
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

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
    public Long save(User user) {
        return null;
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
