package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.RouteRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.RouteResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface RouteService {

    RouteResponseDTO registerRoute(RouteRequestDTO routeRequestDTO);
}
