package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.SeatEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ISeatRepository extends ListCrudRepository<SeatEntity, Integer> {
}