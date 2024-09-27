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
@Table(name = "pelicula_formato", schema = "public")
public class MovieFormatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pelicula_formato_id_gen")
    @SequenceGenerator(name = "pelicula_formato_id_gen", sequenceName = "pelicula_formato_pelfor_id_seq", allocationSize = 1)
    @Column(name = "pelfor_id", nullable = false)
    private Integer id;

    @Column(name = "pelfor_tipo_formato", nullable = false, length = 50)
    private String pelforTipoFormato;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pel_id", nullable = false)
    private MovieEntity pel;

    @OneToMany(mappedBy = "pelfor")
    private Set<SessionFormatEntity> sesionFormatoes = new LinkedHashSet<>();

}