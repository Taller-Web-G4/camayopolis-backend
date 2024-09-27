package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "sesion_formato", schema = "public")
public class SessionFormatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sesion_formato_id_gen")
    @SequenceGenerator(name = "sesion_formato_id_gen", sequenceName = "sesion_formato_sesfor_id_seq", allocationSize = 1)
    @Column(name = "sesfor_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ses_id", nullable = false)
    private SessionEntity ses;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pelfor_id", nullable = false)
    private MovieFormatEntity pelfor;

}