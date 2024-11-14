package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.ProductEntity;
import com.camayopolis.persistence.repository.IProductRepository;
import com.camayopolis.presentation.dto.ProductDto;
import com.camayopolis.service.interfaces.IProductService;
import com.camayopolis.util.mapper.IProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IProductMapper productMapper;

    public ProductServiceImpl(IProductRepository productRepository, IProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productMapper.toDto(productEntities);
    }

    @Override
    public Optional<ProductDto> getProductById(Integer id) {
        if (!productRepository.existsById(id)) {
            return Optional.empty();
        }
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }

    @Override
    public Optional<ProductDto> createProduct(ProductDto productDto) {
        ProductEntity product = productMapper.toEntity(productDto);
        return Optional.of(productMapper.toDto(productRepository.save(product)));
    }

    @Override
    public Optional<ProductDto> updateProduct(Integer id, ProductDto productDto) {
        if (!productRepository.existsById(id)) {
            return Optional.empty();
        }
        ProductEntity product = productMapper.toEntity(productDto);
        product.setId(id);
        return Optional.of(productMapper.toDto(productRepository.save(product)));
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
