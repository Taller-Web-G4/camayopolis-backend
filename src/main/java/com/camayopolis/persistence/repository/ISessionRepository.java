package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.SessionEntity;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface ISessionRepository extends ListCrudRepository<SessionEntity, Integer> {
    List<SessionEntity> findByPel_Id(Integer movieId);
}