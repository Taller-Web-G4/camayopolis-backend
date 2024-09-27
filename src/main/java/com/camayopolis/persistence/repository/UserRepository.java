package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsuCorreo(String usuCorreo);
    boolean existsByUsuCorreo(String usuCorreo);
}