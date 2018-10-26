package com.accenture.be.business.category.converters;

import com.accenture.be.entity.category.Category;
import com.accenture.fe.dto.category.CategoryDTO;

public class CategoryConverter {

    public static CategoryDTO convertToDTO(Category categoryEntity) {
        CategoryDTO categoryDTO = null;
        if(categoryEntity != null) {
            categoryDTO = new CategoryDTO(
                    categoryEntity.getId(), categoryEntity.getName(),
                    categoryEntity.getDescription(), categoryEntity.getImageUrl(),
                    categoryEntity.getStatus()
            );
        }
        return categoryDTO;
    }

    public static Category convertToEntity(CategoryDTO categoryDTO) {
        Category categoryEntity = null;
        if(categoryDTO != null) {
            categoryEntity = new Category(categoryDTO.getId(), categoryDTO.getName(),
                    categoryDTO.getDescription(), categoryDTO.getImageUrl(), categoryDTO.getStatus());
        }
        return categoryEntity;
    }
}
