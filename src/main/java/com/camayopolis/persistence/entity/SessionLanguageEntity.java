package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "sesion_idioma", schema = "public")
public class SessionLanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sesion_idioma_id_gen")
    @SequenceGenerator(name = "sesion_idioma_id_gen", sequenceName = "sesion_idioma_sesidi_id_seq", allocationSize = 1)
    @Column(name = "sesidi_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ses_id", nullable = false)
    private SessionEntity ses;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pelidi_id", nullable = false)
    private MovieLanguageEntity pelidi;

}