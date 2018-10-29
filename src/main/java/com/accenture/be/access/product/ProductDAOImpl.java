package com.accenture.be.access.product;

import com.accenture.be.entity.product.Product;
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

@Repository("productDao")
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /*@Autowired
    private SessionFactory sessionFactory;*/

    @Override
    public List<Product> findAll() {

        List<Product> products = null;
        try {
            TypedQuery<Product> query  = entityManager.createNamedQuery("Product.findAll", Product.class);
            products = query.getResultList();
        } catch (NoResultException nr) {
            products = Collections.emptyList();
        }
        return products;
    }

    @Override
    public Product findById(long productId) {
        Product user = null;
        try {
            TypedQuery<Product> query  = entityManager.createNamedQuery("Product.findById", Product.class)
                    .setParameter("id", productId);
            user = query.getSingleResult();

        }catch (NoResultException nr) {

        }
        return user;
    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        return null;
    }

    @Override
    public List<Product> findByOrder(Long orderId) {
        List<Product> products = null;
        try {
            TypedQuery<Product> query  = entityManager.createNamedQuery("Product.findByOrder", Product.class)
                    .setParameter("orderId", orderId);
            products = query.getResultList();
        } catch (NoResultException nr) {
            products = Collections.emptyList();
        }
        return products;
    }

    @Override
    public Long save(Product product) {

        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long productId = (Long)session.save(product);
        session.getTransaction().commit();
        session.close();
        return productId;*/
        entityManager.persist(product);
        entityManager.flush();
        return product.getId();
    }

    @Override
    public void update(Product product) {
        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();*/
    }

    @Override
    public void delete(long productId) {
        return;
    }
}
