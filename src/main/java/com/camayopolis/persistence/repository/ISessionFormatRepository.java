package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.SessionFormatEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ISessionFormatRepository extends ListCrudRepository<SessionFormatEntity, Integer> {
}