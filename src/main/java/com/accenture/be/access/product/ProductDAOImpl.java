package com.accenture.be.access.product;

import com.accenture.be.entity.product.Product;
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
            nr.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = null;
        try {
            TypedQuery<Product> query  = entityManager.createNamedQuery("Product.findByName", Product.class)
                    .setParameter("name", name);
            products = query.getResultList();
        } catch (NoResultException nr) {
            products = Collections.emptyList();
        }
        return products;
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
        entityManager.persist(product);
        return product.getId();
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void delete(long productId) {
        return;
    }
}
