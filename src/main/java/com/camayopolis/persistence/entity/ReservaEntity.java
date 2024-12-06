package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reserva", schema = "public")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_id_gen")
    @SequenceGenerator(name = "reserva_id_gen", sequenceName = "reserva_res_id_seq", allocationSize = 1)
    @Column(name = "res_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario"))
    private UserEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ses_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sesion"))
    private SessionEntity sesion;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDateTime fechaReserva;

    @Column(name = "butacas", nullable = false, length = 255)
    private String butacas;
}

