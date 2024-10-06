package com.camayopolis.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirmacion_token", schema = "public")
public class ConfirmationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmacion_token_id_gen")
    @SequenceGenerator(name = "confirmacion_token_id_gen", sequenceName = "confirmacion_token_contok_id_seq", allocationSize = 1)
    @Column(name = "contok_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "contok_token", nullable = false)
    private String contokToken;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usu_id", nullable = false)
    private UserEntity usu;

}