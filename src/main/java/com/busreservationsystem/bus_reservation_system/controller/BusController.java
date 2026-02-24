package com.busreservationsystem.bus_reservation_system.controller;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Bus;
import com.busreservationsystem.bus_reservation_system.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BusController {

    //Base URL: /api/buses
    //Handles bus registration and configuration.

    @Autowired
    private BusService busService;


    @PostMapping("/buses")
    public ResponseEntity<BusResponseDTO> createBus(@RequestBody BusRequestDTO busRequestDto)
    {
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(busService.registerBus(busRequestDto));
    }

}
