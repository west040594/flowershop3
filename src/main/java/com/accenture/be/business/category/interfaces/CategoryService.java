package com.accenture.be.business.category.interfaces;

import com.accenture.be.entity.category.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(long categoryId);
    List<Category> findAllCategory();
}
