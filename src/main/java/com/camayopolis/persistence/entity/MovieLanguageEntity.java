package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pelicula_idioma", schema = "public")
public class MovieLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pelicula_idioma_id_gen")
    @SequenceGenerator(name = "pelicula_idioma_id_gen", sequenceName = "pelicula_idioma_pelidi_id_seq", allocationSize = 1)
    @Column(name = "pelidi_id", nullable = false)
    private Integer id;

    @Column(name = "pelidi_nombre_idioma", nullable = false, length = 50)
    private String pelidiNombreIdioma;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pel_id", nullable = false)
    private MovieEntity pel;

    @OneToMany(mappedBy = "pelidi")
    private Set<SessionLanguageEntity> sesionIdiomas = new LinkedHashSet<>();

}