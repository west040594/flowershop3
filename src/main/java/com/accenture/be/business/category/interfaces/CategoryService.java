package com.accenture.be.business.category.interfaces;

import com.accenture.be.entity.category.Category;
import java.util.List;

/**
 * Сервис для взаимодействия с Категориями
 */
public interface CategoryService {

    /** Этот метод найдет категорию по соотвествущему ей id
     * @param categoryId id Категории
     * @return Объект Категории
     */
    Category findCategoryById(long categoryId);

    /** Этот метод найдет все существующие категории
     * @return Коллекция категорий
     */
    List<Category> findAllCategory();
}
