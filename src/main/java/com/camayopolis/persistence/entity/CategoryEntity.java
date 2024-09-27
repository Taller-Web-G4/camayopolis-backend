package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categoria", schema = "public")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_id_gen")
    @SequenceGenerator(name = "categoria_id_gen", sequenceName = "categoria_cat_id_seq", allocationSize = 1)
    @Column(name = "cat_id", nullable = false)
    private Integer id;

    @Column(name = "cat_nombre", nullable = false, length = 100)
    private String catNombre;

    @ManyToMany
    @JoinTable(name = "pelicula_categoria",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "pel_id"))
    private Set<MovieEntity> peliculas = new LinkedHashSet<>();

}