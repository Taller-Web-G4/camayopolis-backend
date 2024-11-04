package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<ProductDto> getAllProducts();
    Optional<ProductDto> getProductById(Integer id);
    boolean existsById(Integer id);
    Optional<ProductDto> createProduct(ProductDto productDTO);
    Optional<ProductDto> updateProduct(Integer id, ProductDto productDTO);
    void deleteProduct(Integer id);
}
