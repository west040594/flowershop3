package com.accenture.be.business.product;

import com.accenture.be.business.category.CategoryConverter;
import com.accenture.be.entity.category.Category;
import com.accenture.be.entity.product.Product;
import com.accenture.fe.dto.product.ProductDTO;

public class ProductConverter {

    public static ProductDTO convertToDTO(Product productEntity) {
        ProductDTO productDTO = null;
        if(productEntity != null) {
            productDTO = new ProductDTO(
                    productEntity.getId(), productEntity.getName(),
                    productEntity.getPrice(), productEntity.getDescription(),
                    productEntity.getImageUrl(), productEntity.getStatus(),
                    productEntity.getInStock(),
                    productEntity.getCreatedAt(), productEntity.getUpdatedAt(),
                    CategoryConverter.convertToDTO(productEntity.getCategory())
            );
        }
        return productDTO;
    }
}
