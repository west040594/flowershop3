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
        return null;
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
    public Long save(Product product) {
        return null;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(long productId) {
        return false;
    }
}
