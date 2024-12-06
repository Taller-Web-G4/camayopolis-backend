package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.ReservationDto;
import com.camayopolis.service.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUser(@PathVariable Integer userId) {
        List<ReservationDto> reservations = reservationService.getReservationsByUser(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{userId}/dates")
    public ResponseEntity<List<ReservationDto>> getReservationsByUserAndDateRange(
            @PathVariable Integer userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<ReservationDto> reservations = reservationService.getReservationsByUserAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(reservations);
    }
}
