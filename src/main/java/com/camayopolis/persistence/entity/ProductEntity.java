package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "producto", schema = "public")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_id_gen")
    @SequenceGenerator(name = "producto_id_gen", sequenceName = "producto_prd_id_seq", allocationSize = 1)
    @Column(name = "prd_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "prd_nombre", nullable = false, length = 100)
    private String prdNombre;

    @Column(name = "prd_descripcion", length = Integer.MAX_VALUE)
    private String prdDescripcion;

    @NotNull
    @Column(name = "prd_precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal prdPrecio;

}