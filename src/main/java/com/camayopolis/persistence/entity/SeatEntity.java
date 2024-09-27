package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "butaca", schema = "public")
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "butaca_id_gen")
    @SequenceGenerator(name = "butaca_id_gen", sequenceName = "butaca_but_id_seq", allocationSize = 1)
    @Column(name = "but_id", nullable = false)
    private Integer id;

    @Column(name = "but_columna", nullable = false)
    private Short butColumna;

    @Column(name = "but_fila", nullable = false, length = Integer.MAX_VALUE)
    private String butFila;

    @ColumnDefault("false")
    @Column(name = "but_ocupado", nullable = false)
    private Boolean butOcupado = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ses_id", nullable = false)
    private SessionEntity ses;

}