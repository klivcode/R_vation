package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.RouteRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.RouteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RouteService {

    RouteResponseDTO registerRoute(RouteRequestDTO routeRequestDTO);

    Page<RouteResponseDTO> getAllRoute(Pageable pageable);

    RouteResponseDTO getRouteById(Long id);

    void deleteRouteById(Long id);

    RouteResponseDTO updateRouteById(Long id, RouteRequestDTO routeRequestDto);

    Page<RouteResponseDTO> searchRoutes(Pageable pageable, String source, String destination,Long companyId);
}
