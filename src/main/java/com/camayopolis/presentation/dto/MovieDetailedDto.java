package com.camayopolis.presentation.dto;

import java.time.LocalDate;
import java.util.List;

public record MovieDetailedDto(
        Integer id,
        String title,
        String synopsis,
        LocalDate openingDate,
        Integer runtime,
        String posterUrl,
        String trailer,
        Boolean isComingSoon,
        Boolean isNewRelease,
        Boolean isPreSale,
        List<CinemaDto> cines
) {
    public record CinemaDto(
            String cine,
            String ciudad
    ) {}
}
