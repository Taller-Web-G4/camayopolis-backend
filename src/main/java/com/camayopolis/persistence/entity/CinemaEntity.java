package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cinema", schema = "public")
public class CinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cinema_id_gen")
    @SequenceGenerator(name = "cinema_id_gen", sequenceName = "cinema_cin_id_seq", allocationSize = 1)
    @Column(name = "cin_id", nullable = false)
    private Integer id;

    @Column(name = "cin_nombre", nullable = false)
    private String cinNombre;

    @Column(name = "cin_telefono", length = 20)
    private String cinTelefono;

    @Column(name = "cin_correo")
    private String cinCorreo;

    @Column(name = "cin_direccion", nullable = false)
    private String cinDireccion;

    @Column(name = "cin_segunda_direccion")
    private String cinSegundaDireccion;

    @Column(name = "cin_ciudad", nullable = false, length = 100)
    private String cinCiudad;

    @Column(name = "cin_latitud", precision = 9, scale = 6)
    private BigDecimal cinLatitud;

    @Column(name = "cin_longitud", precision = 9, scale = 6)
    private BigDecimal cinLongitud;

    @Column(name = "cin_descripcion", length = Integer.MAX_VALUE)
    private String cinDescripcion;

    @Column(name = "cin_cantidad_salas", nullable = false)
    private Integer cinCantidadSalas;

    @Column(name = "cin_nombre_formateado")
    private String cinNombreFormateado;

    @Column(name = "cin_codigo_lealtad", length = 50)
    private String cinCodigoLealtad;

    @OneToMany(mappedBy = "cin")
    private Set<OfferingEntity> servicios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cin")
    private Set<SessionEntity> sesions = new LinkedHashSet<>();

}