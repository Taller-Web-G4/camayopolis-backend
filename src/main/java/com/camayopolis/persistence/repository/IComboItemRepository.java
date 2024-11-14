package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.ComboItemEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IComboItemRepository extends ListCrudRepository<ComboItemEntity, Integer> {
    Integer countComboItemEntitiesByCmb_Id(Integer id);
}