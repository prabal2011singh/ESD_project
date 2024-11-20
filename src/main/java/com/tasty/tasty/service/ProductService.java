package com.tasty.tasty.service;

import com.tasty.tasty.dto.ProductRequest;
import com.tasty.tasty.dto.ProductResponse;
import com.tasty.tasty.entity.Product;
import com.tasty.tasty.mapper.ProductMapper;
import com.tasty.tasty.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductResponse createProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        Product savedProduct = repository.save(product);
        return mapper.toResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProduct(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Optional<Product> existingProduct = repository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(request.name());
            product.setPrice(request.price());
            return mapper.toResponse(repository.save(product));
        }
        throw new RuntimeException("Product not found");
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public List<ProductResponse> getTop2ProductsByPriceRange() {
        return repository.findTop2ProductsByPriceRange()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}