package com.busreservationsystem.bus_reservation_system.controller;


import com.busreservationsystem.bus_reservation_system.dto.request.ScheduleRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.ScheduleResponseDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.SeatLayoutResponseDto;
import com.busreservationsystem.bus_reservation_system.entity.Schedule;
import com.busreservationsystem.bus_reservation_system.repository.ScheduleRepo;
import com.busreservationsystem.bus_reservation_system.services.ScheduleService;
import com.busreservationsystem.bus_reservation_system.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ScheduleController {
    //Base URL: /api/schedules
    //
    //When operator creates schedule → system auto generates dynamic seat layout.
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SeatService seatService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDTO> createSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduleService.createSchedule(scheduleRequestDTO));
    }

    //GET /api/schedules/{scheduleId}/seats
    @GetMapping("/schedules/{scheduleId}/seats")
    public ResponseEntity<SeatLayoutResponseDto> getSeatLayout(@PathVariable long scheduleId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(seatService.getSeatLayout(scheduleId));
    }
}
