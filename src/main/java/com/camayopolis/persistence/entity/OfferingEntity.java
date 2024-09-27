package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "servicio", schema = "public")
public class OfferingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicio_id_gen")
    @SequenceGenerator(name = "servicio_id_gen", sequenceName = "servicio_ser_id_seq", allocationSize = 1)
    @Column(name = "ser_id", nullable = false)
    private Integer id;

    @Column(name = "ser_nombre", nullable = false)
    private String serNombre;

    @Column(name = "ser_descripcion", length = Integer.MAX_VALUE)
    private String serDescripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cin_id", nullable = false)
    private CinemaEntity cin;

}