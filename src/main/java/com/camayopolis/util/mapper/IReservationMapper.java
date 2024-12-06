package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.ReservaEntity;
import com.camayopolis.presentation.dto.ReservationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IReservationMapper {

    @Mapping(source = "sesion.pel.pelTitulo", target = "movieTitle")
    @Mapping(source = "sesion.cin.cinNombre", target = "cinemaName")
    @Mapping(source = "butacas", target = "seats")
    @Mapping(source = "fechaReserva", target = "reservationDate")
    ReservationDto toDto(ReservaEntity reservation);

    List<ReservationDto> toDto(List<ReservaEntity> reservations);
}
