package com.tasty.tasty.mapper;

import com.tasty.tasty.dto.ProductRequest;
import com.tasty.tasty.dto.ProductResponse;
import com.tasty.tasty.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}