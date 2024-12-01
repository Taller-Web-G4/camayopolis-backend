package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.GenderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IGenderRepository extends ListCrudRepository<GenderEntity, Integer> {
}
