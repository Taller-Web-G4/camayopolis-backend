package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.SessionEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ISessionRepository extends ListCrudRepository<SessionEntity, Integer> {
}