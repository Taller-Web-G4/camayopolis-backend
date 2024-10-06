package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pelicula", schema = "public")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pelicula_id_gen")
    @SequenceGenerator(name = "pelicula_id_gen", sequenceName = "pelicula_pel_id_seq", allocationSize = 1)
    @Column(name = "pel_id", nullable = false)
    private Integer id;

    @Column(name = "pel_titulo", nullable = false)
    private String pelTitulo;

    @Column(name = "pel_sinopsis", length = Integer.MAX_VALUE)
    private String pelSinopsis;

    @Column(name = "pel_fecha_estreno", nullable = false)
    private LocalDate pelFechaEstreno;

    @Column(name = "pel_duracion_minutos")
    private Integer pelDuracionMinutos;

    @Column(name = "pel_poster_url")
    private String pelPosterUrl;

    @Column(name = "pel_trailer_url")
    private String pelTrailerUrl;

    @ColumnDefault("false")
    @Column(name = "pel_es_estreno_proximo")
    private Boolean pelEsEstrenoProximo;

    @ColumnDefault("false")
    @Column(name = "pel_es_nuevo_lanzamiento")
    private Boolean pelEsNuevoLanzamiento;

    @ColumnDefault("false")
    @Column(name = "pel_es_preventa")
    private Boolean pelEsPreventa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cla_id")
    private ClassificationEntity cla;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cat_id")
//    private CategoryEntity cat;

//    @ManyToMany(mappedBy = "peliculas")
//    private Set<CategoryEntity> categorias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pel")
    private Set<MovieFormatEntity> peliculaFormatoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pel")
    private Set<MovieLanguageEntity> peliculaIdiomas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pel")
    private Set<SessionEntity> sesions = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "peliculas")
    private Set<CategoryEntity> categorias = new LinkedHashSet<>();

}