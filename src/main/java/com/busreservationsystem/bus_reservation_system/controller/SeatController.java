package com.busreservationsystem.bus_reservation_system.controller;


import com.busreservationsystem.bus_reservation_system.dto.response.SeatResponseDto;
import com.busreservationsystem.bus_reservation_system.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SeatController {

    @Autowired
    private SeatService seatService;

    // GET /api/seats/{seatId} -> return single seat ---> One schedule
    @GetMapping("/seats/{seatId}")
    public ResponseEntity<SeatResponseDto> getSeat(@PathVariable Long seatId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(seatService.getSeatById(seatId));
    }
}
