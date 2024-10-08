package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.ConfirmationTokenEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends ListCrudRepository<ConfirmationTokenEntity, Integer> {
    Optional<ConfirmationTokenEntity> findByContokToken(String token);
}