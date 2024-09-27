package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sesion", schema = "public")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sesion_id_gen")
    @SequenceGenerator(name = "sesion_id_gen", sequenceName = "sesion_ses_id_seq", allocationSize = 1)
    @Column(name = "ses_id", nullable = false)
    private Integer id;

    @Column(name = "ses_hora_inicio", nullable = false)
    private LocalTime sesHoraInicio;

    @Column(name = "ses_nombre_sala", length = 100)
    private String sesNombreSala;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cin_id", nullable = false)
    private CinemaEntity cin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pel_id", nullable = false)
    private MovieEntity pel;

    @OneToMany(mappedBy = "ses")
    private Set<SeatEntity> butacas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ses")
    private Set<SessionFormatEntity> sesionFormatoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ses")
    private Set<SessionLanguageEntity> sesionIdiomas = new LinkedHashSet<>();

}