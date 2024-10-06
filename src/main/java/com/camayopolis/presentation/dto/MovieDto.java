package com.camayopolis.presentation.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record MovieDto(Integer id, String pelTitulo, String pelSinopsis, LocalDate pelFechaEstreno,
                       Integer pelDuracionMinutos, String pelPosterUrl, String pelTrailerUrl,
                       Boolean pelEsEstrenoProximo, Boolean pelEsNuevoLanzamiento,
                       Boolean pelEsPreventa) implements Serializable {
}