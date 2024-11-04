package com.camayopolis.persistence.listener;

import com.camayopolis.persistence.traits.IUpdatable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;

public class UpdateTimestampListener {

    @PrePersist
    public void setCreationTimestamp(Object entity) {
        if (entity instanceof IUpdatable updatableEntity) {
            // Asigna la fecha de creación si está vacía
            if (updatableEntity.getCreationDate() == null) {
                updatableEntity.setCreationDate(Instant.now());
            }
            // Asigna la fecha de actualización al crear
            updatableEntity.setUpdateDate(Instant.now());
        }
    }

    @PreUpdate
    public void setUpdateTimestamp(Object entity) {
        if (entity instanceof IUpdatable updatableEntity) {
            // Asigna solo la fecha de actualización al modificar
            updatableEntity.setUpdateDate(Instant.now());
        }
    }
}
