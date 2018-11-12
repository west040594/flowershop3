package com.accenture.be.business.product.interfaces;

import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.product.exceptions.ProductException;
import com.accenture.be.entity.product.Product;
import com.querydsl.core.types.Predicate;
import java.math.BigDecimal;
import java.util.List;

/**
 * Сервис для работы с продуктами(цветами)
 */
public interface ProductService {
    /**
     * Это метод найдет все существующие продукты
     * @return Колекция продуктов
     */
    List<Product> findAllProduct();

    /**
     * Этот метод найдет все продукты с фильтром по указаному предикату
     * @param predicate Предикат
     * @return Колекция продуктов
     */
    List<Product> findAllProduct(Predicate predicate);

    /**
     * Этот метод найдет все продукты которорые содержат в своем имени указаную строку
     * @param productName Название продукта для совпадения по имени
     * @return Колекия продуктов
     */
    List<Product> findProductByName(String productName);

    /**
     * Этот метод найдет все продукты с ценой в указаном диапазоне
     * @param min Мин цена
     * @param max Макс цена
     * @return Колекия продуктов
     */
    List<Product> findProductByRangePrice(BigDecimal min, BigDecimal max);

    /**
     * Этот метод вернет продукт, который соотвествует указаному Id
     * @param productId Id Продукта
     * @return Объект Продукта
     */
    Product getProductById(long productId);

    /**
     * Этот метод изменит количичество продуктов на складе
     * @param productId Id Продукта
     * @param quantity Количество
     */
    void changeProductQuantityInStock(Long productId, int quantity) throws ProductException;
}
