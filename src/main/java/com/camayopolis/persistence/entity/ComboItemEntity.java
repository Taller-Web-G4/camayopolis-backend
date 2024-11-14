package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "combo_item")
public class ComboItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "combo_item_id_gen")
    @SequenceGenerator(name = "combo_item_id_gen", sequenceName = "combo_item_cmbi_id_seq", allocationSize = 1)
    @Column(name = "cmbi_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "cmbi_cantidad", nullable = false)
    private Integer cmbiCantidad;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "prd_id", nullable = false)
    private ProductEntity prd;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cmb_id")
    private ComboEntity cmb;

}