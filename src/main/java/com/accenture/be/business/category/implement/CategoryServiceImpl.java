package com.accenture.be.business.category.implement;

import com.accenture.be.business.category.interfaces.CategoryService;
import com.accenture.be.entity.category.Category;
import com.accenture.be.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryDao;

    @Override
    public Category findCategoryById(long categoryId) {
        return categoryDao.findById(categoryId).get();
    }

    @Override
    public List<Category> findAllCategory() {
        return (List<Category>) categoryDao.findAll();
    }
}
