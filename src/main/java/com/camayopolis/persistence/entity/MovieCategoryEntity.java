package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pelicula_categoria", schema = "public")
public class MovieCategoryEntity {
    @SequenceGenerator(name = "pelicula_categoria_id_gen", sequenceName = "pelicula_idioma_pelidi_id_seq", allocationSize = 1)
    @EmbeddedId
    private MovieCategoryEntityId pelcatId;

    @MapsId("pelId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pel_id", nullable = false)
    private MovieEntity pel;

    @MapsId("catId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cat_id", nullable = false)
    private CategoryEntity cat;

}