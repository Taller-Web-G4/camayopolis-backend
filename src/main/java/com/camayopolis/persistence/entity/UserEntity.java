package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "usuario_uq", columnNames = {"usu_correo"})
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_gen")
    @SequenceGenerator(name = "usuario_id_gen", sequenceName = "usuario_usu_id_seq", allocationSize = 1)
    @Column(name = "usu_id", nullable = false)
    private Integer id;

    @Column(name = "usu_correo", nullable = false, length = 50)
    private String usuCorreo;

    @Column(name = "usu_contrasena", nullable = false, length = 50)
    private String usuContrasena;

    @Column(name = "usu_rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum usuRol;

    @ColumnDefault("false")
    @Column(name = "usu_activado")
    private Boolean usuActivado;

}