package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clasificacion", schema = "public")
public class ClassificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clasificacion_id_gen")
    @SequenceGenerator(name = "clasificacion_id_gen", sequenceName = "clasificacion_cla_id_seq", allocationSize = 1)
    @Column(name = "cla_id", nullable = false)
    private Integer id;

    @Column(name = "cla_nombre", nullable = false, length = 100)
    private String claNombre;

    @OneToMany(mappedBy = "cla")
    private Set<MovieEntity> peliculas = new LinkedHashSet<>();

}