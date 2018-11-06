package com.accenture.be.repository;

import com.accenture.be.entity.category.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
