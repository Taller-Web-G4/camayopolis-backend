package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.ComboEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IComboRepository extends ListCrudRepository<ComboEntity, Integer> {
}