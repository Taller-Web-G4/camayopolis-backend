package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.CategoryEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ICategoryRepository extends ListCrudRepository<CategoryEntity, Integer> {
}