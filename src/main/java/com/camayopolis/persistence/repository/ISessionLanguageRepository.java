package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.SessionLanguageEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ISessionLanguageRepository extends ListCrudRepository<SessionLanguageEntity, Integer> {
}