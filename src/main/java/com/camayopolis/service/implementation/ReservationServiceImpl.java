package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.ReservaEntity;
import com.camayopolis.persistence.repository.ReservaRepository;
import com.camayopolis.presentation.dto.ReservationDto;
import com.camayopolis.service.interfaces.IReservationService;
import com.camayopolis.util.mapper.IReservationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final ReservaRepository reservaRepository;
    private final IReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservaRepository reservaRepository, IReservationMapper reservationMapper) {
        this.reservaRepository = reservaRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<ReservationDto> getReservationsByUser(Integer userId) {
        List<ReservaEntity> reservations = reservaRepository.findByUsuarioId(userId);
        return reservationMapper.toDto(reservations);
    }

    @Override
    public List<ReservationDto> getReservationsByUserAndDateRange(Integer userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<ReservaEntity> reservations = reservaRepository.findByUsuarioIdAndFechaReservaBetween(userId, startDate, endDate);
        return reservationMapper.toDto(reservations);
    }
}
