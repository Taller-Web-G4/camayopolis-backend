package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.ClassificationEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IClassificationRepository extends ListCrudRepository<ClassificationEntity, Integer> {
}