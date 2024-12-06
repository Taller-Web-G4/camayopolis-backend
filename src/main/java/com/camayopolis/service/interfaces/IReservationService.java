package com.camayopolis.service.interfaces;

import com.camayopolis.persistence.entity.ReservaEntity;
import com.camayopolis.presentation.dto.ReservationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IReservationService {
    List<ReservationDto> getReservationsByUser(Integer userId);

    List<ReservationDto> getReservationsByUserAndDateRange(Integer userId, LocalDateTime startDate, LocalDateTime endDate);
}
