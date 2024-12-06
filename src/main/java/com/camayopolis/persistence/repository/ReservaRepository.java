package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.ReservaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends ListCrudRepository<ReservaEntity, Long> {

    // Buscar reservas por usuario
    List<ReservaEntity> findByUsuarioId(Integer usuarioId);

    // Buscar reservas por usuario y rango de fechas
    List<ReservaEntity> findByUsuarioIdAndFechaReservaBetween(
            Integer usuarioId,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );
}
