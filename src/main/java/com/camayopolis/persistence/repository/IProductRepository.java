package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.ProductEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IProductRepository extends ListCrudRepository<ProductEntity, Integer> {
}