package com.camayopolis.persistence.traits;

import java.time.Instant;

public interface IUpdatable {
    void setCreationDate(Instant creationDate);
    Instant getCreationDate();

    void setUpdateDate(Instant updateDate);
    Instant getUpdateDate();
}
