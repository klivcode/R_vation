package com.busreservationsystem.bus_reservation_system.controller;


import com.busreservationsystem.bus_reservation_system.dto.request.RouteRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.RouteResponseDTO;
import com.busreservationsystem.bus_reservation_system.services.RouteService;
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
public class RouteController {

//Base URL: /api/routes
//
//Manages routes between cities.
    @Autowired
    private RouteService routeService;

    @PostMapping("/routes")
    public ResponseEntity<RouteResponseDTO> registerRoute(@RequestBody RouteRequestDTO routeRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(routeService.registerRoute(routeRequestDTO));
    }

    @GetMapping("/routes/getall")
    public ResponseEntity<Page<RouteResponseDTO>> getAllRoutes(Pageable  pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(routeService.getAllRoute(pageable));
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<RouteResponseDTO> getRoute(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(routeService.getRouteById(id));
    }

    @DeleteMapping("/routes/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRouteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/routes/{id}")
    public ResponseEntity<RouteResponseDTO> updateRoute(@PathVariable Long id,
                                                        @RequestBody RouteRequestDTO routeRequestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(routeService.updateRouteById(id,routeRequestDto));
    }


    // for the search filter by source and destination
    @GetMapping("/routes")
    public  ResponseEntity<Page<RouteResponseDTO>> getRoutes(@ParameterObject Pageable pageable,
                                                             @RequestParam(required = false) String source,
                                                             @RequestParam(required = false) String destination,
                                                             @RequestParam(required = false) Long companyId) {
        return ResponseEntity
                .status(
                        HttpStatus.OK
                ).body(routeService.searchRoutes(pageable,source,destination,companyId));

    }

}
