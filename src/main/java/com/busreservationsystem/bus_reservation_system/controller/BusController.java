package com.busreservationsystem.bus_reservation_system.controller;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Bus;
import com.busreservationsystem.bus_reservation_system.services.BusService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<BusResponseDTO>>  getAllBus(Pageable pageable)
    {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(busService.getAllBus(pageable));
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
    // search (filter By bus number, vip seat, company id,bus type)
    @GetMapping("/buses")
    public ResponseEntity<Page<BusResponseDTO>> searchBus(
            @RequestParam(required = false) String busNumber,
            @RequestParam(required = false) Boolean hasVipSeat,
            @RequestParam(required = false) String busType,
            @RequestParam(required = false) Long companyId,
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(busService.searchBus(
                        busNumber,
                        hasVipSeat,
                        busType,
                        companyId,
                        pageable
                ));
    }


}
