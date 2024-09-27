package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.OfferingEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IOfferingRepository extends ListCrudRepository<OfferingEntity, Integer> {
}