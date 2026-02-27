package com.busreservationsystem.bus_reservation_system.controller;

import com.busreservationsystem.bus_reservation_system.dto.request.BusRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.BusResponseDto;
import com.busreservationsystem.bus_reservation_system.services.BusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<BusResponseDto> createBus(@RequestBody BusRequestDto busRequestDto)
    {
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(busService.registerBus(busRequestDto));
    }


    @GetMapping("/buses/getall")
    public ResponseEntity<Page<BusResponseDto>>  getAllBus(Pageable pageable)
    {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(busService.getAllBus(pageable));
    }
    @GetMapping("/buses/{id}")
    public ResponseEntity<BusResponseDto> getBus(@PathVariable long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(busService.getBusById(id));
    }

    @PutMapping("/buses/{id}")
    public ResponseEntity<BusResponseDto> updateBus(@RequestBody BusRequestDto busRequestDto,
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
//    @Tag(name = "Bus API", description = "APIs for managing and searching buses")
//    @Operation(
//            summary = "Search Buses",
//            description = "Search buses using optional filters like bus number, VIP seat availability, bus type, and company ID with pagination support."
//    )

    @GetMapping("/buses")
    public ResponseEntity<Page<BusResponseDto>> searchBus(
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
