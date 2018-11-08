package com.accenture.be.repository;

import com.accenture.be.entity.product.Product;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Репозиторий для работы с сущностями Product
 */
public interface ProductRepository extends CrudRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    /**
     * Этот метод найдет все продукты которые содержат указаную строку в своем имени
     * @param name Имя продукта для совпадения
     * @return Колекция продуктов
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Этот метод найдет все продукты у которых цена соотвествует данному диапазону
     * @param min Мин цена
     * @param max Макс цена
     * @return Колекция продуктов
     */
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
}
