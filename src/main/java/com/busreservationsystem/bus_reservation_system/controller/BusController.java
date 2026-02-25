package com.busreservationsystem.bus_reservation_system.controller;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Bus;
import com.busreservationsystem.bus_reservation_system.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/buses/getall")
    public ResponseEntity<List<BusResponseDTO>>  getAllBus()
    {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(busService.getAllBus());
    }
    @GetMapping("/buses/{id}")
    public ResponseEntity<BusResponseDTO> getBus(@PathVariable long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(busService.getBusById(id));
    }

    @PutMapping("/buses/{id}")
    public ResponseEntity<BusResponseDTO> updateBus(@RequestBody  BusRequestDTO busRequestDto,
                                                    @PathVariable long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(busService.updateBusById(busRequestDto,id));
    }
    @DeleteMapping("/buses/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busService.deleteBusById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
