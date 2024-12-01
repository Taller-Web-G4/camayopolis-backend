package com.camayopolis.persistence.entity;

import com.camayopolis.persistence.traits.IUpdatable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "genero")
public class GenderEntity implements IUpdatable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genero_id_gen")
    @SequenceGenerator(name = "genero_id_gen", sequenceName = "genero_gen_id_seq", allocationSize = 1)
    @Column(name = "gen_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "gen_nombre", nullable = false, length = 50)
    private String genNombre;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "gen_fecha_creacion", updatable = false)
    private Instant genFechaCreacion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "gen_fecha_actualizacion")
    private Instant genFechaActualizacion;

    @Override
    public void setCreationDate(Instant creationDate) {
        this.genFechaCreacion = creationDate;
    }

    @Override
    public Instant getCreationDate() {
        return this.genFechaCreacion;
    }

    @Override
    public void setUpdateDate(Instant fechaActualizacion) {
        this.genFechaActualizacion = fechaActualizacion;
    }

    @Override
    public Instant getUpdateDate() {
        return this.genFechaActualizacion;
    }

}