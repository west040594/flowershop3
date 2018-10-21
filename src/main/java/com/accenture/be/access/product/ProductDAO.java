package com.accenture.be.access.product;

import com.accenture.be.entity.product.Product;
import java.util.List;

public interface ProductDAO {

    /**
     * Возвращает полный список объектов Product
     * @return Лист Product
     */
    List<Product> findAll();

    /**
     * Возвращает конкретный экземпляр Product, представленный указанным ID
     * @param productId ID Продукта
     * @return Product объект
     */
    Product findById(long productId);


    /**
     * Возвращает список объектов Product, которые содержат указанное имя
     * @param name Название Продукта
     * @return Лист Product
     */
    List<Product> findByName(String name);

    /**
     * Возвращает список объектов Product, которые содержат указанное имя категории
     * @param categoryName Название категории
     * @return Лист Product
     */
    List<Product> findByCategory(String categoryName);


    /**
     * Этот метод попытается вставить Product в репозиторий.
     * @param product Объект Продукта для вставки в репозиторий
     * @return Идентификатор записи в репозитории
     */
    Long save(Product product);


    /**
     * Обновляет существующий Продукт в репозитории со значениями,
     * представленными объектом Product, переданными в качестве параметра.
     * Параметр Product должен содержать идентификатор, соответствующий существующему ID в репозитории,
     * и все поля должны быть заполнены и действительны.
     * @param product Объект Продукта содержащий информацию для обновления
     * @return true, если строка была обновлена.
     */
    boolean update(Product product);


    /**
     * Удаляет существующий Продукт в репозитории, представленный указанным ID
     * @param productId ID Продукта
     * @return true, если строка была удалена
     */
    boolean delete(long productId);

}
