package com.busreservationsystem.bus_reservation_system.services;

import com.busreservationsystem.bus_reservation_system.dto.request.RouteRequestDto;
import com.busreservationsystem.bus_reservation_system.dto.response.RouteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RouteService {

    RouteResponseDto registerRoute(RouteRequestDto routeRequestDTO);

    Page<RouteResponseDto> getAllRoute(Pageable pageable);

    RouteResponseDto getRouteById(Long id);

    void deleteRouteById(Long id);

    RouteResponseDto updateRouteById(Long id, RouteRequestDto routeRequestDto);

    Page<RouteResponseDto> searchRoutes(Pageable pageable, String source, String destination, Long companyId);
}
