package com.camayopolis.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieDTO {
    private Integer id;
    private String pelTitulo;
    private String pelSinopsis;
    private LocalDate pelFechaEstreno;
    private Integer pelDuracionMinutos;
    private String pelPosterUrl;
    private String pelTrailerUrl;
    private Boolean pelEsEstrenoProximo;
    private Boolean pelEsNuevoLanzamiento;
    private Boolean pelEsPreventa;
}
