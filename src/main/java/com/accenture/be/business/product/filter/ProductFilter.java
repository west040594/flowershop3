package com.accenture.be.business.product.filter;

import com.accenture.be.entity.product.QProduct;
import com.querydsl.core.BooleanBuilder;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Фильтер отображения продуктов(цветов) на странице
 */
public class ProductFilter {

    private String name;
    private String categoryId;
    private String maxPrice;
    private String minPrice;

    public BooleanBuilder getPredicate() {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QProduct qProduct = QProduct.product;

        if(name != null && !name.isEmpty()) {
            try {
                byte ptext[] = name.getBytes("ISO-8859-1");
                name = new String(ptext, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            booleanBuilder.and(qProduct.name.containsIgnoreCase(name));
        }

        if(categoryId != null && !categoryId.isEmpty()) {
            booleanBuilder.and(qProduct.category.id.eq(Long.parseLong(categoryId)));
        }

        if(maxPrice != null && minPrice != null) {
            booleanBuilder.and(qProduct.price.between(new BigDecimal(minPrice),
                    new BigDecimal(maxPrice)));
        }

        return booleanBuilder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }
}
