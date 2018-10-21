package com.accenture.be.access.category;

import com.accenture.be.entity.category.Category;

import java.util.List;

public interface CategoryDAO {

    /**
     * Возвращает полный список объектов Category
     * @return Лист Category
     */
    List<Category> findAll();

    /**
     * Возвращает конкретный экземпляр Category, представленный указанным ID
     * @param categoryId ID Категории
     * @return Category объект
     */
    Category findById(long categoryId);


    /**
     * Возвращает список объектов Category, которые содержат указанное имя
     * @param name Название Категории
     * @return Лист Category
     */
    List<Category> findByName(String name);


    /**
     * Этот метод попытается вставить Category в репозиторий.
     * @param category Объект Категории для вставки в репозиторий
     * @return Идентификатор записи в репозитории
     */
    Long save(Category category);


    /**
     * Обновляет существующую Категорию в репозитории со значениями,
     * представленными объектом Category, переданными в качестве параметра.
     * Параметр Category должен содержать идентификатор, соответствующий существующему ID в репозитории,
     * и все поля должны быть заполнены и действительны.
     * @param category Объект Категории содержащий информацию для обновления
     * @return true, если строка была обновлена.
     */
    boolean update(Category category);


    /**
     * Удаляет существующую Категорию в репозитории, представленный указанным ID
     * @param categoryId ID Категории
     * @return true, если строка была удалена
     */
    boolean delete(long categoryId);
}
