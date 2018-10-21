package com.accenture.be.business.category;

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
}
