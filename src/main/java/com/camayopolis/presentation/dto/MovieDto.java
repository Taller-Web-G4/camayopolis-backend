package com.camayopolis.presentation.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record MovieDto(
        Integer id,
        String title,
        String synopsis,
        LocalDate openingDate,
        Integer runtime,
        String posterUrl,
        String trailer,
        Boolean isComingSoon,
        Boolean isNewRelease,
        Boolean isPreSale
) implements Serializable {
}