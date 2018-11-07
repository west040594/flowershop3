package com.accenture.be.business.product.specification;

import com.accenture.be.business.category.interfaces.CategoryService;
import com.accenture.be.entity.category.Category;
import com.accenture.be.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;


public class ProductSpecification implements Specification<Product> {

    @Autowired
    private CategoryService categoryService;

    private String name;
    private String categoryId;
    private String maxPrice;
    private String minPrice;

    public ProductSpecification(String name, String categoryId, String maxPrice, String minPrice) {
        super();
        this.name = name;
        this.categoryId = categoryId;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public Predicate toPredicate(Root<Product> root,
                                 CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.disjunction();

        if(name != null) {
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("name"), name));
        }

        if(categoryId != null) {
            Category category = categoryService.findCategoryById(Long.parseLong(categoryId));
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("category"), category));
        }

        if(maxPrice != null && minPrice != null) {
            predicate.getExpressions().add(criteriaBuilder.between(root.get("price"),
                    new BigDecimal(minPrice),
                    new BigDecimal(maxPrice)));
        }

        return predicate;
    }
}
