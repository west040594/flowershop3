package com.accenture.be.repository;

import com.accenture.be.entity.category.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для работы с сущностями Category
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
