package com.camayopolis.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MovieCategoryEntityId implements Serializable {
    private static final long serialVersionUID = -5431201610528189043L;
    @Column(name = "pel_id", nullable = false)
    private Integer pelId;

    @Column(name = "cat_id", nullable = false)
    private Integer catId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieCategoryEntityId entity = (MovieCategoryEntityId) o;
        return Objects.equals(this.catId, entity.catId) &&
                Objects.equals(this.pelId, entity.pelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catId, pelId);
    }

}