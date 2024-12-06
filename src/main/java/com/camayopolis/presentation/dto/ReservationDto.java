package com.camayopolis.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDto {
    private Long id;
    private String movieTitle;
    private String cinemaName;
    private String seats;
    private LocalDateTime reservationDate;
}

