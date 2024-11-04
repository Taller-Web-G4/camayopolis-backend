package com.camayopolis.persistence.listener;

import com.camayopolis.persistence.traits.IUpdatable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;

public class UpdateTimestampListener {

    @PrePersist
    public void setCreationTimestamp(Object entity) {
        if (entity instanceof IUpdatable updatableEntity) {
            if (updatableEntity.getCreationDate() == null) {
                updatableEntity.setCreationDate(Instant.now());
            }
            updatableEntity.setUpdateDate(Instant.now());
        }
    }

    @PreUpdate
    public void setUpdateTimestamp(Object entity) {
        if (entity instanceof IUpdatable updatableEntity) {
            updatableEntity.setUpdateDate(Instant.now());
        }
    }
}
