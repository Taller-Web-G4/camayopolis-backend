package com.camayopolis.persistence.entity;

import com.camayopolis.persistence.listener.UpdateTimestampListener;
import com.camayopolis.persistence.traits.IUpdatable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "combo", schema = "public")
@EntityListeners(UpdateTimestampListener.class)
public class ComboEntity implements IUpdatable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "combo_id_gen")
    @SequenceGenerator(name = "combo_id_gen", sequenceName = "combo_cmb_id_seq", allocationSize = 1)
    @Column(name = "cmb_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "cmb_nombre", nullable = false, length = 100)
    private String cmbNombre;

    @Column(name = "cmb_descripcion", length = Integer.MAX_VALUE)
    private String cmbDescripcion;

    @NotNull
    @Column(name = "cmb_precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal cmbPrecio;

    @Size(max = 255)
    @Column(name = "cmb_imagen_url")
    private String cmbImagenUrl;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "cmb_fecha_creacion", updatable = false)
    private Instant cmbFechaCreacion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "cmb_fecha_actualizacion")
    private Instant cmbFechaActualizacion;

    @Override
    public void setCreationDate(Instant creationDate) {
        this.cmbFechaCreacion = creationDate;
    }

    @Override
    public Instant getCreationDate() {
        return this.cmbFechaCreacion;
    }

    @Override
    public void setUpdateDate(Instant fechaActualizacion) {
        this.cmbFechaActualizacion = fechaActualizacion;
    }

    @Override
    public Instant getUpdateDate() {
        return this.cmbFechaActualizacion;
    }

}