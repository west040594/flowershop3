package com.accenture.be.business.product.converters;

import com.accenture.be.business.category.converters.CategoryConverter;
import com.accenture.be.entity.product.Product;
import com.accenture.fe.dto.product.ProductDTO;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ProductDTO> convertToDto(List<Product> productEntities) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : productEntities) {
            ProductDTO productDTO = ProductConverter.convertToDTO(product);
            if(productDTO != null) {
                productDTOS.add(productDTO);
            }
        }
        return productDTOS;
    }

    public static Product convertToEntity(ProductDTO productDTO) {
        Product productEntity = null;
        if(productDTO != null) {
            productEntity = new Product(
                    productDTO.getId(), productDTO.getName(), productDTO.getPrice(), productDTO.getDescription(),
                    productDTO.getImageUrl(), productDTO.getStatus(), productDTO.getInStock(),
                    productDTO.getCreatedAt(), productDTO.getUpdatedAt(),
                    CategoryConverter.convertToEntity(productDTO.getCategory()));
        }
        return productEntity;
    }
}
