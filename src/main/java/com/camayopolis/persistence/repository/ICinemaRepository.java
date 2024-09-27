package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.CinemaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ICinemaRepository extends ListCrudRepository<CinemaEntity, Integer> {
}